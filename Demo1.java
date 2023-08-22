import java.util.*;
class Demo1{
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Give me a Name: ");
        String name = scanner.nextLine();
        System.out.println(isValidAccNum(name));

        
        

        
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