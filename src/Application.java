import java.io.Console;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TopDownParser parser = new TopDownParser();
        while (true){
            System.out.println("Type in an regular expression:");
            String regExp = sc.nextLine();
            Visitable tree = parser.parse(regExp);
            if(tree!=null){
                DepthFirstIterator.traverse(tree,new VisitorOne());
                DepthFirstIterator.traverse(tree,new VisitorTwo());
                System.out.println("This is a regular expression. Tree has bee created and both Visitors visited the tree");
            }
        }


    }
}
