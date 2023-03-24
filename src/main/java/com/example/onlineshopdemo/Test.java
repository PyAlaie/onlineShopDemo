package com.example.onlineshopdemo;

public class Test {
    public static void importTestCases(){
        // Admins
        Admin admin1 = new Admin("arshia", "1234", "test@test.com");
        Admin admin2 = new Admin("sara", "1234", "test@test.com");
        Admin admin3 = new Admin("hooman", "1234", "test@test.com");

        OnlineShop.addAdmin(admin1);
        OnlineShop.addAdmin(admin2);
        OnlineShop.addAdmin(admin3);

        // Sellers
        Seller seller1 = new Seller("Rana", "1234", "test@test.com");
        Seller seller2 = new Seller("mamad", "1234", "test@test.com");
        Seller seller3 = new Seller("amirhosien", "1234", "test@test.com");

        OnlineShop.addSeller(seller1);
        OnlineShop.addSeller(seller2);
        OnlineShop.addSeller(seller3);

        // Costumers
        Costumer costumer1 = new Costumer("mahla", "1234", "test@test.com");
        Costumer costumer2 = new Costumer("hasti", "1234", "test@test.com");
        Costumer costumer3 = new Costumer("farid", "1234", "test@test.com");

        OnlineShop.addCostumer(costumer1);
        OnlineShop.addCostumer(costumer2);
        OnlineShop.addCostumer(costumer3);

        // Categories
        Category category1 = new Category("Electronics");
        Category category2 = new Category("Toys");
        Category category3 = new Category("Clothes");

        SubCategory subCategory1 = new SubCategory(category1, "phones");
        SubCategory subCategory2 = new SubCategory(category1, "laptops");
        SubCategory subCategory3 = new SubCategory(category1, "earphones");
        SubCategory subCategory4 = new SubCategory(category2, "dollies");
        SubCategory subCategory5 = new SubCategory(category2, "cars");
        SubCategory subCategory6 = new SubCategory(category2, "guns");
        SubCategory subCategory7 = new SubCategory(category3, "T-Shirts");
        SubCategory subCategory8 = new SubCategory(category3, "jeans");
        SubCategory subCategory9 = new SubCategory(category3, "socks");

        OnlineShop.addCategory(category1);
        OnlineShop.addCategory(category2);
        OnlineShop.addCategory(category3);

        OnlineShop.addSubCategory(subCategory1);
        OnlineShop.addSubCategory(subCategory2);
        OnlineShop.addSubCategory(subCategory3);
        OnlineShop.addSubCategory(subCategory4);
        OnlineShop.addSubCategory(subCategory5);
        OnlineShop.addSubCategory(subCategory6);
        OnlineShop.addSubCategory(subCategory7);
        OnlineShop.addSubCategory(subCategory8);
        OnlineShop.addSubCategory(subCategory9);

        // Products
        Product product1 = new Product("Xiaomi", 100,4, seller1, subCategory1);
        Product product2 = new Product("lenovo", 300,2, seller2, subCategory2);
        Product product3 = new Product("Raycon", 50,10, seller3, subCategory3);
        Product product4 = new Product("anable", 10,1, seller1, subCategory4);
        Product product5 = new Product("benz", 5,4, seller2, subCategory5);
        Product product6 = new Product("AK-47", 10,4, seller3, subCategory6);
        Product product7 = new Product("red t-shirt", 20,6, seller1, subCategory7);
        Product product8 = new Product("jean west", 50,2, seller2, subCategory8);
        Product product9 = new Product("just a normal pair of socks", 5, 2, seller3, subCategory9);

        OnlineShop.addProduct(product1);
        OnlineShop.addProduct(product2);
        OnlineShop.addProduct(product3);
        OnlineShop.addProduct(product4);
        OnlineShop.addProduct(product5);
        OnlineShop.addProduct(product6);
        OnlineShop.addProduct(product7);
        OnlineShop.addProduct(product8);
        OnlineShop.addProduct(product9);
    }
}
