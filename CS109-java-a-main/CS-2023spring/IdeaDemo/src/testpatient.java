import java.util.Scanner;
public class testpatient {
    private String patientname;
    private char patientsex;
    private int patientage;
    private float patientweight;
    public String get_patientname(){
        return patientname;
    }
    public void set_patientname(String name){
        patientname=name;
    }
    public char get_patientsex(){
        return patientsex;
    }
    public void set_patientsex(char sex){
        patientsex=sex;
    }
    public int get_patientage(){
        return patientage;
    }
    public void set_patientage(int age){
        patientage=age;
    }
    public float get_patientweight(){
        return patientweight;
    }
    public void set_patientweight(float weight){
        patientweight=weight;
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        testpatient patient_infor=new testpatient();
        testpatient patient_infor2=new testpatient();
        patient_infor.set_patientname("muzi");
        patient_infor.set_patientsex('F');
        patient_infor2.set_patientage(23);
        patient_infor2.set_patientweight(50.0000f);
        System.out.println("Name: "+patient_infor.get_patientname());
        System.out.println("Sex: "+patient_infor.get_patientsex());
        System.out.println("Age: "+patient_infor2.get_patientage());
        System.out.println("Weight: "+patient_infor2.get_patientweight());
    }
}
