

import com.javagoshi.Pet;




public class Teste01 {
    public static void main(String[] args) {
        Pet pedro = new Pet("Pedro");
        pedro.setFome(60);
        
        System.out.println(pedro.getFome());
        
        
        pedro.comer('C');
        System.out.println(pedro.getFome());
        
        
        
        
        
    }
    
}
