package ru.stqa.lessons.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.lessons.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-d",description = "Target file")
  public String file;

  @Parameter (names = "-f",description = "Data format")
  public String format;
  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;}
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if(format.equals("xml")) {
      saveAsXml(contacts,new File(file));
    }  else if(format.equals("json")) {
      saveAsJson(contacts,new File(file));
    }else {
      System.out.println("Unrecognized format"+format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for(int i = 0; i<count; i++){
      contacts.add(new ContactData().withFistn(String.format("name %s",i)).withLastn(String.format("last name %s",i))
              .withAddress(String.format("ul. Tipanova, d. 5, kv. %s",i)).withEmail(String.format("test%s1@test.ts",i))
              .withEmail2(String.format("test%s2@test.ts",i)).withEmail3(String.format("test%s3@test.ts",i))
              .withHomep(String.format("+%s (123)",i)).withMobilep(String.format("32-1%s",i))
              .withWorkp(String.format("%s 123",i)));
    }
    return contacts;
  }
}
