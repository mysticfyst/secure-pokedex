package processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import annotations.EncryptedField;
import com.google.auto.service.AutoService;


@SupportedAnnotationTypes("annotations.EncryptedField")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Messager messager = processingEnv.getMessager();

    for (TypeElement annotation : annotations) {

      // Even though this processor just processes 'EncryptedField' annotations, I am adding
      // this check just in case we might need another annotation for nested classes.
      if (annotation.getSimpleName().toString().equals("EncryptedField")) {

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(EncryptedField.class);

        for (Element element : annotatedElements) {
          if (!ElementKind.FIELD.equals(element.getKind())) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                "@EncryptedField can only be applied to a field");
          } else if (false) {
            // TODO: maybe check if the field is of type String ?

          }
        }

        List<Element> validFields = new ArrayList<>(annotatedElements);

        Map<String, List<Element>> classToFieldsMap = new HashMap<>();
        validFields.forEach(field -> {
          String className =
              ((TypeElement) field.getEnclosingElement()).getQualifiedName().toString();
          if (classToFieldsMap.get(className) == null) {
            List<Element> fields = new ArrayList<>();
            fields.add(field);
            classToFieldsMap.put(className, fields);
          } else {
            classToFieldsMap.get(className).add(field);
          }
        });

        writeBuilderFile(classToFieldsMap);

      }
    }

    return true;
  }

  private void writeBuilderFile(Map<String, List<Element>> classToFieldsMap) {
    try {

      String encrypterClassName = "Encrypter";

      JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(encrypterClassName);
      try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

        // Hardcoding package for now
        out.println("package com.pokedex.pojo;");
        out.println();

        // Adding a static import
        // TODO: Maybe autogenerate this based on the functions used?
        out.println("import com.pokedex.utility.CommonUtil;");
        out.println();

        out.print("public class ");
        out.print(encrypterClassName);
        out.println(" {");
        out.println();

        classToFieldsMap.forEach((className, fields) -> {
          addMethodForEncryption(className, fields, out);
        });

        out.println("}");

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addMethodForEncryption(String className, List<Element> fields, PrintWriter out) {
    // Add comments for the encrypter function
    out.println("\t/** ");
    out.println("\t * Fields to be encrypted: " + fields.toString());
    out.println("\t */");

    // Add the method of encryption
    out.print("\tpublic static ");
    out.print(className);
    out.print(" encryptObject(");
    out.print(className);
    out.println(" objectToBeEncrypted) {");
    out.println();
    for (Element field : fields) {
      encryptField("objectToBeEncrypted", field.getSimpleName().toString(), 2, out);
    }
    out.println("\t\treturn objectToBeEncrypted;");
    out.println("\t}");
  }


  public void encryptField(String objectName, String fieldName, int tabs, PrintWriter out) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(objectName).append(".").append(getSetterName(fieldName)).append("(")
        .append("CommonUtil.encrypt(").append(objectName).append(".")
        .append(getGetterName(fieldName)).append("()").append(")").append(")").append(";");
    addTabs(tabs, out);
    out.println("// Encrypting " + fieldName);
    addTabs(tabs, out);
    out.println(stringBuilder.toString());
    out.println();
  }

  public void decryptField(String objectName, String fieldName, int tabs, PrintWriter out) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(objectName).append(".").append(getSetterName(fieldName)).append("(")
        .append("CommonUtil.decrypt(").append(objectName).append(".")
        .append(getGetterName(fieldName)).append("()").append(")").append(")").append(";");
    addTabs(tabs, out);
    out.println("// Decrypting " + fieldName);
    addTabs(tabs, out);
    out.println(stringBuilder.toString());
    out.println();
  }

  private String getFieldType(Element element) {
    return (element.asType()).toString();
  }

  public String getSetterName(String fieldName) {
    return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  }

  public String getGetterName(String fieldName) {
    return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  }

  public void addTabs(int tabs, PrintWriter out) {
    for (int i = 0; i < tabs; i++) {
      out.print("\t");
    }
  }

}