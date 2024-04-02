package org.question5.documents;
import org.bson.Document;
import java.util.Date;
public class Person extends Document {
  private String id;
  private String name;
  private int age;
  private String phone;
  private float height;
  private String email;
  private String cpf;
  private Date birthday;
  private Address address;
}