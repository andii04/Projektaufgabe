public class Application {
    public static void main(String[] args) {
        Parser p = new Parser();
        p.Parse();
        Visitable visitable=p.GetTree();
        DepthFirstIterator.traverse(visitable,new VisitorOne());
        DepthFirstIterator.traverse(visitable,new VisitorTwo());
        System.out.println("fertig");

        //https://stackoverflow.com/questions/26948724/create-syntax-tree-from-prograiven-regular-expressions-for-re-to-dfa

    }
}
