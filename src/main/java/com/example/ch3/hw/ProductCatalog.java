package com.example.ch3.hw;

import java.util.ArrayList;

public class ProductCatalog {
    private ArrayList<Product> products;

    public ProductCatalog() {
        products = new ArrayList<>();
    }

    // 상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }

    // 상품 조회
    public Product getProduct(int index) {
        return products.get(index);
    }

    // 상품 삭제
    public void removeProduct(int index) {
        products.remove(index);
    }

    // 상품 목록 출력
    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    // 테스트
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Product("노트북", 1500000));
        catalog.addProduct(new Product("스마트폰", 800000));
        catalog.addProduct(new Product("태블릿", 600000));

        // 상품 목록 출력
        catalog.displayProducts();

        // 특정 상품 조회
        System.out.println("두 번째 상품: " + catalog.getProduct(1));

        // 상품 삭제
        catalog.removeProduct(0);
        catalog.displayProducts();
    }
}

class Product {
    private String name;
    private int price; // 가격 (원)

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // toString 메서드 오버라이드
    @Override
    public String toString() {
        return name + " - " + price + "원";
    }
}
