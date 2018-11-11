package EffectiveJava.Item3_Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Serialization refers to a process of converting an object into a format which can be persisted to disk
 * (for example saved to a file or a datastore), sent through streams (for example stdout), or sent over a network.
 * The format in which an object is serialized into, can either be binary or structured text (for example XML, JSON YAML…).
 * JSON and XML are two of the most commonly used serialization formats within web applications.
 *
 * Deserialization on the other hand, is the opposite of serialization,
 * that is, transforming serialized data coming from a file, stream or network socket into an object.
 *
 *
 */

class Singleton {
  public static final Singleton INSTANCE = new Singleton();
  private int value;
  private Singleton() {}
  public void setValue(int value) {
    this.value = value;
  }
  public int getValue() {
    return this.value;
  }

  //Solution for serialize singleton violation:
  protected Object readResolve() {
    return INSTANCE;
  }

}

/**
 * Two objects are not same
 * 2
 * 1
 * The solution is that we have to implement the readResolve method,
 * which is called when preparing the deserialized object before returning it to the caller.
 * The solution is as follows.
 *
 *
 */
public class SerializeIssue implements Serializable {
  public static void main(String[] args) {
    Singleton singleton = Singleton.INSTANCE;
    singleton.setValue(1);
    // Serialize
    try {
      FileOutputStream fileOut = new FileOutputStream("out.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(singleton);
      out.close();
      fileOut.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    singleton.setValue(2);
    // Deserialize
    Singleton singleton2 = null;
    try {
      FileInputStream fileIn = new FileInputStream("out.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      singleton2 = (Singleton) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
    } catch (ClassNotFoundException c) {
      System.out.println("singletons.SingletonEnum class not found");
      c.printStackTrace();
    }
    if (singleton == singleton2) {
      System.out.println("Two objects are same");
    } else {
      System.out.println("Two objects are not same");
    }
    System.out.println(singleton.getValue());
    System.out.println(singleton2.getValue());
  }
}