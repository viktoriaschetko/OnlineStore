public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store(" Online Store");
        Product productObject = new Product();
        Category categoryObject = new Account();
        String[] splittedInfoProd;
        String[] splittedInfoCat;


        ArrayList<String> productList = new ArrayList<String>();
        Product = new Product("Products.txt");

        for (int i = 0; i < productsList.size(); i++) {
            String account = productsList.get(i);
            productObject.setProductName(splittedInfoProd[0]);
            productObject.setRate(int.valueofInfoProd[1]);

            productObject.setPrice(Double.valueOf(splittedInfoProd[3]));
            productObject = new Product(productObject.getName(), productObject.getName(), productObject.getRate(), productObject.getPrice());


            store.setArrProd(productObject);
        } System.out.println(productObject.getPrice());


        System.out.println("^^^^^^ Welcome to our " + store.getName() + " ^^^^^");


        do {
            System.out.println("Choose the required operations from the list below:\n ( (1) Display all products \n), (2) Enter the name of the product;
                    choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.println("The list of available products: \n");
                for (int i = 0; i < productList.size(); i++) {
                    System.out.println(productList.get(i));
                }


            } else if (choice == 2) {
                System.out.println("Enter the name of the product: ");
                String productName = input1.next();
                for (int i = 0; i < productList.size(); i++) {
                    String infoLine = productList.get(i);
                    String[] CheckName = infoLine.split(",");
                    if ((CheckID[0].equals(productName))) {
                        TF = true;
                        break;
                    }
                }else{
                    System.out.println("Oops! incorrect product name");
                }
                double Total = (Q * productObject.getPrice());
                System.out.println(Total);
            }
            case :
                System.out.println("Thanks for visiting our store!");
                System.exit(0);
            default:
                System.out.println("Incorrect Entry! Try agian!");
                break;

    } } }