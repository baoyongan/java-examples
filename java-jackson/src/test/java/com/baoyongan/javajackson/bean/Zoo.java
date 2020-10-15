package com.baoyongan.javajackson.bean;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class Zoo {
    public Animal animal;

    public Zoo(Dog dog) {
        this.animal=dog;
    }

    public Zoo() {
    }

    @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME, 
      include = JsonTypeInfo.As.PROPERTY,
      property = "type")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    public static class Animal {
        public String name;

        public Animal(String name) {
            this.name = name;
        }

        public Animal() {
        }
    }
 
    @JsonTypeName("dog")
    public static class Dog extends Animal {
        public double barkVolume;

        public Dog(String lacy) {
            super(lacy);
        }
    }
 
    @JsonTypeName("cat")
    public static class Cat extends Animal {
        boolean likesCream;
        public int lives;

        public Cat(String name) {
            super(name);
        }

        public Cat() {
        }
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "animal=" + animal +
                '}';
    }
}