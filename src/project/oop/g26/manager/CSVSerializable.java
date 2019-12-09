package project.oop.g26.manager;

public interface CSVSerializable<T> {

    String toCSVString();

    T toInstance(String[] strings);

}
