import java.util.Scanner;

class CliSmartBanking_App2 {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        final String clear = "\033[H\033[2J";
        final String color_Blue = "\033[1;34m",color_green = "\033[0;32m",Purple = "\033[0;35m",reset ="\033[0m",Yellow = "\033[0;33m",GREEN_BACKGROUND = "\033[42m";

        final String Dashboard = "Smart Banking System";
        final String Open_New_Acc = "Open New Account";
        final String Deposit_Money = "Deposit Money";
        final String WithDraw_Money = "Withdraw Money";
        final String Transfer_Money = "Transfer Money";
        final String Acc_Bal = "Check Account Balance";
        final String Del_Acc = "Delete Account";
        
        String screen = Dashboard;
        String name;
        String initdeposit = "";
        String id;
        String error;
    

        //Arrays
        String[][] customers = new String[0][3] ;//{{"SDB-001","amal","7000"},{"SDB-002","wimal","7500"},{"SDB-003","amal","80000"}}

        main_loop:
        do{
            //Title
            System.out.print(clear);
            String line = String.format("%s%s%s",color_green,"-".repeat(60),reset);
            String title = String.format("%s%s%s%s",color_Blue," ".repeat((60 - screen.length())/2),screen.toUpperCase(),reset);
            System.out.println(line);
            System.out.println(title);
            System.out.println(line);
            lbl_main:
            switch (screen){
                
                case Dashboard:
                    System.out.println("[1]. Open New Account");
                    System.out.println("[2]. Deposit Money");
                    System.out.println("[3]. Withdraw Money");
                    System.out.println("[4]. Transfer Money");
                    System.out.println("[5]. Check Account Balance");
                    System.out.println("[6]. Delete Account");
                    System.out.println("[7]. Exit");
                    System.out.print("Enter an Option to Continue >> ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch(option){
                        case 1 : screen = Open_New_Acc;break;
                        case 2 : screen = Deposit_Money;break;
                        case 3 : screen = WithDraw_Money;break;
                        case 4 : screen = Transfer_Money;break;
                        case 5 : screen = Acc_Bal;break;
                        case 6 : screen = Del_Acc;break;
                        case 7 : System.exit(0);break;
                        //default : continue;
                    }
                    break;
                
                case Open_New_Acc:
                    //Generate Acc number
                    boolean validName = false;
                    int x = 1, initDepo;
    
                    loop_name:
                    while(true){
                        id = String.format("SDB-%05d",x);
                        System.out.printf("New Account number => %s%s%s\n",Purple,id,reset);
                        System.out.print("Enter name: ");
                        name = scanner.nextLine().strip();
                        //name validation
                        if(!ErrorMsgName(name).equalsIgnoreCase("valid")) {
                            System.out.println(ErrorMsgName(name));
                            System.out.print("Do you want Enter a valid name? (Y/N) >> ");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_name;
                            else screen = Dashboard;break loop_name;
                            
                        }
                        
                        //Initial Desposit
                        do{
                            System.out.print("Initial Deposit: ");
                            initDepo = scanner.nextInt();
                            scanner.nextLine();
                            if(initDepo < 5000){
                                System.out.print("Insufficient Deposit.Do you want Deposit sufficient amount(Y/N): ");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                else screen = Dashboard; break loop_name;
                                
                            }else{
                                System.out.printf("Account number %s%s%s of %s\033[1;30m%s%s has been created\n",Yellow,id,reset,GREEN_BACKGROUND,name.toUpperCase(),reset);
                                initdeposit += initDepo;
                                break;
                            }
                        }while(true);
                        //Names Store in a array
                        
                        String[][] newcustomer = new String[customers.length + 1][3];
                        for (int i = 0; i < customers.length; i++) {
                            newcustomer[i] = customers[i];
                        }
                        newcustomer[newcustomer.length - 1][0] = id; 
                        newcustomer[newcustomer.length - 1][1] = name; 
                        newcustomer[newcustomer.length - 1][2] = initdeposit; 
                        customers = newcustomer;

                    }
                    
                case Deposit_Money:
                    {
                    boolean exist =false;
                    String accNum;
                    int amount;
                    int existingCustomerIndex =0;
                    loop_DepoMoney:
                    do{
                        System.out.print("Enter Account number: ");
                        accNum = scanner.nextLine();
                        //Input Acc number validation
                        if(customers.length == 0){
                            System.out.println("No Records Found");
                            System.out.print("Insufficient Deposit.Do you want Deposit sufficient amount(Y/N): ");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                else screen = Dashboard; break loop_DepoMoney;
                        }
                        //check exist
                        loop_exist:
                        if(existingCustomer(accNum, customers) == -1){
                            System.out.print("Customer Doesn't Exist. Do you want to try again(Y/N) >>");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_DepoMoney;
                            else screen = Dashboard; break loop_DepoMoney;
                        }
                        System.out.printf("Welcome %s \nCurrent Balance is: Rs%,d.00\n",customers[existingCustomer(accNum, customers)][1],Integer.valueOf(customers[existingCustomerIndex][2]));

                        //add deposit
                        System.out.print("Enter deposit amount: ");
                        amount = scanner.nextInt();
                        scanner.nextLine();
                        //validation
                        if (amount <= 500) {
                            System.out.print("Insufficient Deposit. Do you want to try again(Y/N) >>");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_DepoMoney;
                            else screen = Dashboard; break lbl_main;
                        }
                        int avalBal = Integer.valueOf(customers[existingCustomerIndex][2]);
                        avalBal += amount;
                        System.out.printf("New Balance is %s\n",avalBal);

                        System.out.print("Do you want to make Deposit Again(Y/N) >>");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_DepoMoney;
                            else screen = Dashboard; break lbl_main;
                    
                    }while(true);
                    }
                case WithDraw_Money:
                    {
                    boolean exist =false;
                    String accNum;
                    int amount;
                    int existingCustomerIndex =0;
                    loop_withdrawMoney:
                    do{
                        System.out.print("Enter Account number: ");
                        accNum = scanner.nextLine();
                        //Input Acc number validation
                        if(!(accNum.startsWith("SDB-") || accNum.length() == 9)){
                            System.out.print("Invalid Account number. Do you want to try again(Y/N) >>");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            else screen = Dashboard; break ;
                        }else{
                            for (int i = 4; i < accNum.length(); i++) {
                                if(!Character.isDigit(accNum.charAt(i))){
                                    System.out.print("Invalid Account number. Do you want to try again(Y/N) >>");
                                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                                    else screen = Dashboard; break loop_withdrawMoney;
                                }
                            }
                        }
                        //check exist
                        loop_exist:
                        for (int i = 0; i < customers.length; i++) {
                            String cus = customers[i][0];
                            if(accNum.equals(cus)){
                                exist = true;
                                break;
                            }
                        }
                        if(!exist){
                            System.out.print("Customer Doesn't Exist. Do you want to try again(Y/N) >>");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                            else screen = Dashboard; break loop_withdrawMoney;
                        }
                        System.out.printf("Welcome %s \nCurrent Balance is: Rs%,d.00\n",customers[existingCustomerIndex][1],Integer.valueOf(customers[existingCustomerIndex][2]));

                        //add deposit
                        System.out.print("Enter Withdraw amount: ");
                        amount = scanner.nextInt();
                        scanner.nextLine();
                        //validation
                        if (amount <= 100) {
                            System.out.print("Insufficient Deposit. Do you want to try again(Y/N) >>");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                            else screen = Dashboard; break lbl_main;
                        }
                        int avalBal = Integer.valueOf(customers[existingCustomerIndex][2]);
                        avalBal -= amount;
                        System.out.printf("New Balance is %s\n",avalBal);

                        System.out.print("Do you want to make Withdraw Again(Y/N) >>");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                            else screen = Dashboard; break lbl_main;
                    
                    }while(true);
                    }
                
                case Transfer_Money:
                    {
                        boolean exist =false;
                        String fromAccNum,toAccNum;
                        int amount;
                        int existingCustomerIndex =0;
                        loop_DepoMoney:
                        do{
                            System.out.print("Enter Account number Transfer From: ");
                            fromAccNum = scanner.nextLine();
                            //Input Acc number validation
                            if(!(fromAccNum.startsWith("SDB-") || fromAccNum.length() == 9)){
                                System.out.print("Invalid Account number. Do you want to try again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                else screen = Dashboard; break ;
                            }else{
                                for (int i = 4; i < fromAccNum.length(); i++) {
                                    if(!Character.isDigit(fromAccNum.charAt(i))){
                                        System.out.print("Invalid Account number. Do you want to try again(Y/N) >>");
                                        if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_DepoMoney;
                                        else screen = Dashboard; break loop_DepoMoney;
                                    }
                                }
                            }
                            //check exist
                            loop_exist:
                            for (int i = 0; i < customers.length; i++) {
                                String cus = customers[i][0];
                                if(fromAccNum.equals(cus)){
                                    exist = true;
                                    break;
                                }
                            }
                            if(!exist){
                                System.out.print("Customer Doesn't Exist. Do you want to try again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_DepoMoney;
                                else screen = Dashboard; break loop_DepoMoney;
                            }
                            System.out.printf("Welcome %s \nCurrent Balance is: Rs%,d.00\n",customers[existingCustomerIndex][1],Integer.valueOf(customers[existingCustomerIndex][2]));

                            //add deposit
                            System.out.print("Enter Withdraw amount: ");
                            amount = scanner.nextInt();
                            scanner.nextLine();
                            //validation
                            if (amount <= 100) {
                                System.out.print("Insufficient Deposit. Do you want to try again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_DepoMoney;
                                else screen = Dashboard; break lbl_main;
                            }
                            int avalBal = Integer.valueOf(customers[existingCustomerIndex][2]);
                            avalBal -= amount;
                            System.out.printf("New Balance is %s\n",avalBal);

                            System.out.print("Do you want to make Withdraw Again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_DepoMoney;
                                else screen = Dashboard; break lbl_main;
                        
                        }while(true);
                    }
                
                case Acc_Bal:
                    {
                        boolean exist =false;
                        String accNum;
                        int existingCustomerIndex =0;
                        loop_withdrawMoney:
                        do{
                            System.out.print("Enter Account number: ");
                            accNum = scanner.nextLine();
                            //Input Acc number validation
                            if(!(accNum.startsWith("SDB-") || accNum.length() == 9)){
                                System.out.print("Invalid Account number. Do you want to try again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                else screen = Dashboard; break ;
                            }else{
                                for (int i = 4; i < accNum.length(); i++) {
                                    if(!Character.isDigit(accNum.charAt(i))){
                                        System.out.print("Invalid Account number. Do you want to try again(Y/N) >>");
                                        if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                                        else screen = Dashboard; break loop_withdrawMoney;
                                    }
                                }
                            }
                            //check exist
                            loop_exist:
                            for (int i = 0; i < customers.length; i++) {
                                String cus = customers[i][0];
                                if(accNum.equals(cus)){
                                    exist = true;
                                    break;
                                }
                            }
                            if(!exist){
                                System.out.print("Customer Doesn't Exist. Do you want to try again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                                else screen = Dashboard; break loop_withdrawMoney;
                            }
                            System.out.printf("Account Holder name of %s \nCurrent Balance is: Rs%,d.00\n",customers[existingCustomerIndex][1],Integer.valueOf(customers[existingCustomerIndex][2]));
                            System.out.printf("Available withdraw amount Rs.%,d.00",Integer.valueOf(customers[existingCustomerIndex][2]) - 500);

                            System.out.print("Do you want to make check Available balance  Again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                                else screen = Dashboard; break lbl_main;
                        
                        }while(true);
                    }
                case Del_Acc:
                    {
                        boolean exist =false;
                        String accNum;
                        int existingCustomerIndex =0;
                        loop_withdrawMoney:
                        do{
                            System.out.print("Enter Account number to Delete: ");
                            accNum = scanner.nextLine();
                            //Input Acc number validation
                            if(!(accNum.startsWith("SDB-") || accNum.length() == 9)){
                                System.out.print("Invalid Account number. Do you want to try again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                else screen = Dashboard; break ;
                            }else{
                                for (int i = 4; i < accNum.length(); i++) {
                                    if(!Character.isDigit(accNum.charAt(i))){
                                        System.out.print("Invalid Account number. Do you want to try again(Y/N) >>");
                                        if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                                        else screen = Dashboard; break loop_withdrawMoney;
                                    }
                                }
                            }
                            //check exist
                            loop_exist:
                            for (int i = 0; i < customers.length; i++) {
                                String cus = customers[i][0];
                                if(accNum.equals(cus)){
                                    exist = true;
                                    break;
                                }
                            }
                            if(!exist){
                                System.out.print("Customer Doesn't Exist. Do you want to try again(Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                                else screen = Dashboard; break loop_withdrawMoney;
                            }
                            System.out.printf("Account Holder name of %s Deleted Successfully!!\n",customers[existingCustomerIndex][1]);

                            System.out.print("Do you want to Delete Another account (Y/N) >>");
                                if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue loop_withdrawMoney;
                                else screen = Dashboard; break lbl_main;
                        
                        }while(true);
                    }
                
            }
        }while(true);
    }


    public static String ErrorMsgName(String name){
        name.strip();
        String output = "";

        if(name.isBlank()){
            output = "Name Cannot be Empty";
            return output;
        }
        for (int i = 0; i < name.length(); i++) {
            if(!(Character.isLetter(name.charAt(i)) || name.charAt(i) == ' ')){
                output = "Invalid Name!!";
                return output;
            }
        }

        return "Valid";


    }

    public static int existingCustomer(String accNum, String[][] customers){
        for (int i = 0; i < customers.length; i++) {
            if(accNum.equals(customers[i][0])) return i;
        }

        return -1;
    }

    public static boolean isValidAccNum(String accNum){
        if(!(accNum.startsWith("SDB-") || accNum.length() == 9)){
            return false;
        }else{
            for (int i = 4; i < accNum.length(); i++) {
                if(!Character.isDigit(accNum.charAt(i))){
                    return false;
                }
            }
        }
        return true;
    }


}
