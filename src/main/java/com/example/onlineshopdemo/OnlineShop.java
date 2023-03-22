package com.example.onlineshopdemo;

import java.util.ArrayList;

public class OnlineShop {

    private static String name;
    private static String webAddress;
    private static String supportPhoneNumber;
    private static int totalProfit;
    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private static ArrayList<Seller> sellers = new ArrayList<Seller>();
    private static ArrayList<Costumer> costumers = new ArrayList<Costumer>();
    private static ArrayList<FundRequest> fundRequests = new ArrayList<FundRequest>();
    private static ArrayList<Product> products = new ArrayList<Product>();
    private static ArrayList<Order> orders = new ArrayList<Order>();
    private static ArrayList<Cart> carts = new ArrayList<Cart>();

    private static Admin loggedInAdmin = null;
    private static Seller loggedInSeller = null;
    private static Costumer loggedInCostumer = null;
    private static User loggedInUser = null;
    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<FundRequest> getFundRequests() {
        return fundRequests;
    }

    public static String getName() {
        return name;
    }

    public static ArrayList<Seller> getSellers() {
        return sellers;
    }

    public static void setName(String name) {
        OnlineShop.name = name;
    }

    public static String getWebAddress() {
        return webAddress;
    }

    public static void setWebAddress(String webAddress) {
        OnlineShop.webAddress = webAddress;
    }

    public static String getSupportPhoneNumber() {
        return supportPhoneNumber;
    }

    public static void setSupportPhoneNumber(String supportPhoneNumber) {
        OnlineShop.supportPhoneNumber = supportPhoneNumber;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        OnlineShop.loggedInUser = loggedInUser;
    }

    public static void main(String[] args) {
//        Admin a = new Admin("arshia","1234","asdas");
//        OnlineShop shop = new OnlineShop();
//        shop.getLoggedInCostumer().getCarts().get(0).addItem(new Item());
//        shop.addAdmin(a);
//        System.out.println(shop.getAdmins());
    }

    public static Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    public static Seller getLoggedInSeller() {
        return loggedInSeller;
    }

    public static Costumer getLoggedInCostumer() {
        return loggedInCostumer;
    }

    public static void addAdmin(Admin admin){
        if(!doesAdminExist(admin.username)){
            admins.add(admin);
        }
    }

    public static boolean doesAdminExist(String username){
        for(Admin admin : admins){
            if(admin.username.equals(username)){
                return true;
            }
        }
        return false;
    }

    public static boolean authenticateAdmin(String username, String password){
        for(Admin admin : admins){
            if(admin.username.equals(username) && admin.password.equals(password)){
                return true;
            }
        }
        return false;
    }

    public static void loginAdmin(String username){
        for(Admin admin : admins){
            if(admin.username.equals(username)){
                loggedInAdmin = admin;
                loggedInUser = admin;
            }
        }
    }

    public static void logoutAdmin(){
        loggedInAdmin = null;
        loggedInUser = null;
    }

    public static ArrayList<FundRequest> getUncheckedFundRequests(){
        ArrayList<FundRequest> res = new ArrayList<>();
        for(FundRequest req : fundRequests){
            if(!req.isChecked()){
                res.add(req);
            }
        }
        return res;
    }

    public static ArrayList<Seller> getUncheckedSellers(){
        ArrayList<Seller> res = new ArrayList<>();
        for(Seller req : sellers){
            if(!req.isChecked()){
                res.add(req);
            }
        }
        return res;
    }
    public static ArrayList<Order> getUncheckedOrders(){
        ArrayList<Order> res = new ArrayList<>();
        for(Order order : orders){
            if(!order.isChecked()){
                res.add(order);
            }
        }
        return res;
    }

    public static void addSeller(Seller seller){
        if(!doesSellerExist(seller.username)){
            sellers.add(seller);
        }
    }

    public static boolean doesSellerExist(String username){
        for(Seller seller : sellers){
            if(seller.username.equals(username)){
                return true;
            }
        }
        return false;
    }

    public static boolean authenticateSeller(String username, String password){
        for(Seller seller : sellers){
            if(seller.username.equals(username) && seller.password.equals(password)){
                return true;
            }
        }
        return false;
    }

    public static void loginSeller(String username){
        for(Seller seller : sellers){
            if(seller.username.equals(username)){
                loggedInSeller = seller;
                loggedInUser = seller;
            }
        }
    }

    public static void logoutSeller(){
        loggedInSeller = null;
        loggedInUser = null;
    }

    public static void addProduct(Product product){
        if(!doesProductExist(product.getName())){
            product.setSeller(loggedInSeller);
            products.add(product);
        }
    }

    public static boolean doesProductExist(String name){
        for(Product product : products){
            if(product.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static void addCostumer(Costumer costumer){
        if(!doesCostumerExist(costumer.username)){
            costumers.add(costumer);
        }
    }

    public static boolean doesCostumerExist(String username){
        for(Costumer costumer : costumers){
            if(costumer.username.equals(username)){
                return true;
            }
        }
        return false;
    }

    public static boolean authenticateCostumer(String username, String password){
        for(Costumer costumer : costumers){
            if(costumer.username.equals(username) && costumer.password.equals(password)){
                return true;
            }
        }
        return false;
    }

    public static void loginCostumer(String username){
        for(Costumer costumer : costumers){
            if(costumer.username.equals(username)){
                loggedInCostumer = costumer;
                loggedInUser = costumer;
            }
        }
    }

    public static void logoutCostumer(){
        loggedInCostumer = null;
        loggedInUser = null;
    }

    public static void addCartToLoggedInCostumer(){
        if(loggedInCostumer != null){
            loggedInCostumer.addCart();
        }
    }

    public static void orderCart(Cart cart){
        Order order = cart.checkout(loggedInCostumer);
        orders.add(order);
    }

    public static boolean isBlank(String input){
        if(input.equals("")){
            return true;
        }
        return false;
    }

    public static void addFundRequest(FundRequest fundRequest){
        if(!fundRequests.contains(fundRequest)){
            fundRequests.add(fundRequest);
        }
    }
}
