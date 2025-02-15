package com.example.ch4.functions.test;

import java.util.function.Function;

class Product {
    String name;
    int price;
    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

public class ProductStringConverter {
    public static void main(String[] args) {
        Product product = new Product("Book", 12000);

        // 단일 기능: 상품 -> 문자열 변환
        Function<Product, String> productToString = p ->
                "상품명: " + p.name + ", 가격: " + p.price + "원";

        String result = productToString.apply(product);
        System.out.println(result); // 기대 출력: "상품명: Book, 가격: 12000원"
    }
}
