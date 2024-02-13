public class shopping {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int n = input.nextInt();
        int m = input.nextInt();
        for(int i=0;i<n;i++){

        }

        output.close();
    }

}


class product_list{
    public product head;

    public void add_to_list(product pro){

    }

}

class product {
    int val;

    product next;

    public product(int val) {
        this.val = val;
    }
}
