/**
 * CS 251: Data Structures and Algorithms
 * Project 1: Part 2
 *
 * TODO: Complete Maze.
 *
 * @author , Sofia Meza
 * @username , meza8
 * @sources TODO: list your sources here
 *
 *
 *
 */
public class Maze {

    private final char SPACE = '.';
    private final char WALL = '#';
    private final char START = '$';
    private final char END = '%';

    class Item {
        private int row;
        private int column;
        private int direction; // 0=down, 1=right, 2=up, 3=left

        public Item(int row, int column) {
            this.row = row;
            this.column = column;
            this.direction = 0; //starts looking down, therefore starts as 0
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return column;
        }

        public int getDirection() {
            return this.direction;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String positionString() {
            return ("(" + this.row + "," + this.column + ")");
        }
    }

    /**
     * Finds the path using CyclicDoubleQueue as a traditional stack
     * Returns the path and number of spaces checked as {@code String}.
     *
     * @param  map a {@code char[][]} provide.
     * @return the path and number of spaces checked as {@code String}
     */
    public String solveWithStack(char[][] map) throws Exception {
        /*

        int steps = 0;
        int row = 0;
        int column = 0;
        String toReturn = "";

        CyclicDoubleQueue<Item> queue = new CyclicDoubleQueue<>();
        CyclicDoubleQueue<Item> everythingQueue = new CyclicDoubleQueue<>();

        boolean visited[][] = new boolean[map.length][map[0].length]; //positions that have already been visited
        visited[row][column] = true;

        Item node = new Item(row,column); //first node from starting position

        queue.enqueueBack(node);
        everythingQueue.enqueueBack(node);

        boolean goldFound = false;
        while (!goldFound) {

            if (queue.isEmpty()) {
                return "no way";
            }

            node = (Item) queue.dequeueBack();
            steps++;
            row = node.getRow();
            column = node.getColumn();

            //System.out.println(node.positionString());

            if (row + 1 < map.length && map[row + 1][column] == END) { //search down for end
                visited[row + 1][column] = true;
                Item temp = new Item(row + 1, column);
                queue.enqueueBack(temp);
                everythingQueue.enqueueBack(temp);
                goldFound = true;
                //steps++;
            }
            if (column + 1 < map[0].length && map[row][column + 1] == END) { //search right for end
                visited[row][column + 1] = true;
                Item temp = new Item(row, column + 1);
                queue.enqueueBack(temp);
                everythingQueue.enqueueBack(temp);
                goldFound = true;
                //steps++;
            }

            if (row - 1 >= 0 && map[row - 1][column] == END) { //search up for end
                visited[row - 1][column] = true;
                Item temp = new Item(row - 1, column);
                queue.enqueueBack(temp);
                everythingQueue.enqueueBack(temp);
                goldFound = true;
                //steps++;
            }

            if (column - 1 >= 0 && map[row][column - 1] == END) { // search left for end
                visited[row][column - 1] = true;
                Item temp = new Item(row, column - 1);
                queue.enqueueBack(temp);
                everythingQueue.enqueueBack(temp);
                goldFound = true;
                //steps++;
            }

            if (!goldFound) {
                if (row + 1 < map.length && map[row + 1][column] == SPACE && !visited[row + 1][column]) { //search down for SPACE
                    visited[row + 1][column] = true;
                    Item temp = new Item(row + 1, column);
                    queue.enqueueBack(temp);
                    everythingQueue.enqueueBack(temp);
                    //steps++;
                }


                if (column + 1 < map[0].length && map[row][column + 1] == SPACE && !visited[row][column + 1]) { //search right for SPACE
                    visited[row][column + 1] = true;
                    Item temp = new Item(row, column + 1);
                    queue.enqueueBack(temp);
                    everythingQueue.enqueueBack(temp);
                    // steps++;

                }

                if (row - 1 >= 0 && map[row - 1][column] == SPACE && !visited[row - 1][column]) { //search up for SPACE

                    visited[row - 1][column] = true;
                    Item temp = new Item(row - 1, column);
                    queue.enqueueBack(temp);
                    everythingQueue.enqueueBack(temp);
                    //steps++;
                }

                if (column - 1 >= 0 && map[row][column - 1] == SPACE && !visited[row][column - 1]) { // search left for SPACE
                    visited[row][column - 1] = true;
                    Item temp = new Item(row, column - 1);
                    queue.enqueueBack(temp);
                    everythingQueue.enqueueBack(temp);
                    //steps++;
                }
            }

        }

        /*
        System.out.println("everythingQueue at the end is "+ everythingQueue.getSize());
        int num = everythingQueue.getSize();
        for (int i = 0; i < num; i++) {
            Item tempNode = new Item(0,0);
            tempNode = (Item) everythingQueue.dequeueFront();
            System.out.print(tempNode.positionString());
        }
          //Print everythingQueue

        //TODO go through everythingQUEUE buscar el node que este mas cerca de end en el queue Y que este adjacent
        //peekBack = last node
        //peek front = starting position

        CyclicDoubleQueue<Item> resultQueue = new CyclicDoubleQueue<>(); // result queue
        resultQueue.enqueueBack((Item) queue.dequeueBack()); // getting the gold position as the last element of the reuslts queue


        Item frontResultQueue = (Item) resultQueue.peekBack(); //getting the first element of results queue

        //System.out.println(frontResultQueue.positionString());

        Item backEverythingQueue = (Item) everythingQueue.dequeueBack();

        while (!frontResultQueue.positionString().equals("(0,0)")) {
            backEverythingQueue = (Item) everythingQueue.peekBack();

            if (frontResultQueue.positionString().equals(backEverythingQueue.positionString())) {
                backEverythingQueue = (Item) everythingQueue.dequeueBack();
            } else if (frontResultQueue.getRow() == backEverythingQueue.getRow()) {
                if (frontResultQueue.getColumn() == backEverythingQueue.getColumn() + 1 || frontResultQueue.getColumn() == backEverythingQueue.getColumn() - 1) {
                    //System.out.println("this are adjacent, switching from everything queue to result queue");
                    resultQueue.enqueueFront((Item) everythingQueue.dequeueBack());

                } else {
                    everythingQueue.dequeueBack();
                }
            } else if (frontResultQueue.getColumn() == backEverythingQueue.getColumn()) {
                if (frontResultQueue.getRow() == backEverythingQueue.getRow() + 1 || frontResultQueue.getRow() == backEverythingQueue.getRow() - 1) {
                    //System.out.println("this are adjacent, switching from everything queue to result queue");
                    resultQueue.enqueueFront((Item) everythingQueue.dequeueBack());
                } else {
                    everythingQueue.dequeueBack();
                }
            } else {
                everythingQueue.dequeueBack();
            }

            frontResultQueue = (Item) resultQueue.peekFront();
            // System.out.println(frontResultQueue.positionString());
        }

        /*
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count - 1);



        int size = resultQueue.getSize();
        for (int i = 0; i < size; i++) {
            Item temp = (Item) resultQueue.dequeueFront();
            toReturn = toReturn + temp.positionString();
        }

        toReturn = toReturn + " " + steps;


        return toReturn;
        */

        int steps = 0;

        int row = 0;
        int column = 0;
        String toReturn = "";

        CyclicDoubleQueue<Item> stack = new CyclicDoubleQueue<>();
        CyclicDoubleQueue<Item> inOrderStack = new CyclicDoubleQueue<>();

        boolean visited[][] = new boolean[map.length][map[0].length]; //positions that have already been visited

        Item node = new Item(row,column); //first node from starting position

        stack.enqueueFront(node);

        while(!stack.isEmpty()) {
            node = (Item) stack.peekFront();

            int direction = node.getDirection();
            row = node.getRow();
            column = node.getColumn();


            node.setDirection(node.getDirection()+1);

            if(map[row][column] == END) {
                int tempSize = stack.getSize();

                //print for testing purposes
                for (int i = 0; i < tempSize; i++) {
                    inOrderStack.enqueueFront(stack.dequeueBack());
                    Item tempNode = new Item(0,0);
                    tempNode = (Item) inOrderStack.dequeueFront();
                    toReturn = toReturn + tempNode.positionString();
                }

                toReturn = toReturn + " " + (steps);

                return toReturn;
            }

            switch (direction) {
                case 0: //left
                    if(column-1 >= 0 && map[row][column-1] == SPACE && !visited[row][column-1]) {
                        //System.out.println("left");
                        //System.out.println("steps rn " + steps);
                        Item temp = new Item(row, column - 1);
                        stack.enqueueFront(temp);

                        visited[row][column - 1] = true;
                        steps = steps + 1;

                    }
                    break;

                case 1: //up
                    if (row - 1 >= 0 && map[row-1][column] == SPACE && !visited[row-1][column]) {
                        //System.out.println("up");
                        //System.out.println("steps rn " + steps);
                        Item temp = new Item(row-1,column);
                        stack.enqueueFront(temp);

                        visited[row-1][column] = true;
                        steps = steps + 1;

                    }
                    break;

                case 2: //right
                    if (column + 1 < map[0].length && map[row][column+1] == SPACE && !visited[row][column+1]) {
                        //System.out.println("rright");
                        //System.out.println("steps rn " + steps);

                        Item temp = new Item(row,column+1);
                        stack.enqueueFront(temp);

                        visited[row][column+1] = true;
                        steps = steps + 1;

                    }
                    break;

                case 3: //down
                    if (row+1 < map.length && map[row + 1][column] == SPACE && !visited[row+1][column]) {
                        //System.out.println("down");
                        //System.out.println("steps rn " + steps);
                        Item temp = new Item(row+1, column);
                        stack.enqueueFront(temp);

                        visited[row+1][column] = true;
                        steps = steps + 1;

                    }
                    break;
                default:
                    visited[row][column] = true;
                    stack.dequeueFront();
                    break;
            }

            if (column-1 >= 0 && map[row][column-1] == END) { //left
                //System.out.println("seeing gold left");
                Item temp = new Item(row, column - 1);
                if (!visited[row][column - 1]) {
                    visited[row][column - 1] = true;
                    steps = steps + 1;
                }
                stack.enqueueFront(temp);
            } else if (row - 1 >= 0 && map[row-1][column] == END) { //up
                //System.out.println("Seeing gold up");
                Item temp = new Item(row-1,column);
                if (!visited[row-1][column]) {
                    visited[row-1][column] = true;
                    steps = steps + 1;
                }
                stack.enqueueFront(temp);
            } else if (column + 1 < map[0].length && map[row][column+1] == END) { //right
                //System.out.println("seeing gold right");
                Item temp = new Item(row, column + 1);
                if (!visited[row][column + 1]) {
                    visited[row][column + 1] = true;
                    steps = steps + 1;
                }
                stack.enqueueFront(temp);
            } else if (row + 1 < map.length &&map[row + 1][column] == END) { //down
                //System.out.println("seeing gold down");
                Item temp = new Item(row+1, column);
                if (!visited[row + 1][column]) {
                    visited[row + 1][column] = true;
                    steps = steps + 1;
                }
                stack.enqueueFront(temp);
            }

        }

        return "no way";

    }

    /**
     * Finds the path using CyclicDoubleQueue as a traditional queue.
     * Returns the path and number of spaces checked as {@code String}.
     *
     * @param  map a {@code char[][]} provide.
     * @return the path and number of spaces checked as {@code String}
     */
    public String solveWithQueue(char[][] map) throws Exception {
        int steps = 0;
        int row = 0;
        int column = 0;
        String toReturn = "";

        CyclicDoubleQueue<Item> queue = new CyclicDoubleQueue<>();
        CyclicDoubleQueue<Item> everythingQueue = new CyclicDoubleQueue<>();

        boolean visited[][] = new boolean[map.length][map[0].length]; //positions that have already been visited
        visited[row][column] = true;

        Item node = new Item(row,column); //first node from starting position

        queue.enqueueBack(node);
        everythingQueue.enqueueBack(node);

        boolean goldFound = false;
        while (!goldFound) {

            if (queue.isEmpty()) {
                return "no way";
            }

            node = (Item) queue.dequeueFront();
            steps++;
            row = node.getRow();
            column = node.getColumn();

            //System.out.println(node.positionString());

            if (row + 1 < map.length && map[row + 1][column] == END) { //search down for end
                visited[row + 1][column] = true;
                Item temp = new Item(row + 1, column);
                queue.enqueueBack(temp);
                everythingQueue.enqueueBack(temp);
                goldFound = true;
                //steps++;
            }
            if (column + 1 < map[0].length && map[row][column + 1] == END) { //search right for end
                visited[row][column + 1] = true;
                Item temp = new Item(row, column + 1);
                queue.enqueueBack(temp);
                everythingQueue.enqueueBack(temp);
                goldFound = true;
                //steps++;
            }

            if (row - 1 >= 0 && map[row - 1][column] == END) { //search up for end
                visited[row - 1][column] = true;
                Item temp = new Item(row - 1, column);
                queue.enqueueBack(temp);
                everythingQueue.enqueueBack(temp);
                goldFound = true;
                //steps++;
            }

            if (column - 1 >= 0 && map[row][column - 1] == END) { // search left for end
                visited[row][column - 1] = true;
                Item temp = new Item(row, column - 1);
                queue.enqueueBack(temp);
                everythingQueue.enqueueBack(temp);
                goldFound = true;
                //steps++;
            }

            if (!goldFound) {
                if (row + 1 < map.length && map[row + 1][column] == SPACE && !visited[row + 1][column]) { //search down for SPACE
                    visited[row + 1][column] = true;
                    Item temp = new Item(row + 1, column);
                    queue.enqueueBack(temp);
                    everythingQueue.enqueueBack(temp);
                    //steps++;
                }


                if (column + 1 < map[0].length && map[row][column + 1] == SPACE && !visited[row][column + 1]) { //search right for SPACE
                    visited[row][column + 1] = true;
                    Item temp = new Item(row, column + 1);
                    queue.enqueueBack(temp);
                    everythingQueue.enqueueBack(temp);
                   // steps++;

                }

                if (row - 1 >= 0 && map[row - 1][column] == SPACE && !visited[row - 1][column]) { //search up for SPACE

                    visited[row - 1][column] = true;
                    Item temp = new Item(row - 1, column);
                    queue.enqueueBack(temp);
                    everythingQueue.enqueueBack(temp);
                    //steps++;
                }

                if (column - 1 >= 0 && map[row][column - 1] == SPACE && !visited[row][column - 1]) { // search left for SPACE
                    visited[row][column - 1] = true;
                    Item temp = new Item(row, column - 1);
                    queue.enqueueBack(temp);
                    everythingQueue.enqueueBack(temp);
                    //steps++;
                }
            }

        }

        /*
        System.out.println("everythingQueue at the end is "+ everythingQueue.getSize());
        int num = everythingQueue.getSize();
        for (int i = 0; i < num; i++) {
            Item tempNode = new Item(0,0);
            tempNode = (Item) everythingQueue.dequeueFront();
            System.out.print(tempNode.positionString());
        }
         */ //Print everythingQueue

        //TODO go through everythingQUEUE buscar el node que este mas cerca de end en el queue Y que este adjacent
                        //peekBack = last node
                        //peek front = starting position

        CyclicDoubleQueue<Item> resultQueue = new CyclicDoubleQueue<>(); // result queue
        resultQueue.enqueueBack((Item) queue.dequeueBack()); // getting the gold position as the last element of the reuslts queue

        Item frontResultQueue = (Item) resultQueue.peekBack(); //getting the first element of results queue

        //System.out.  (frontResultQueue.positionString());

        Item backEverythingQueue = (Item) everythingQueue.dequeueBack();

        while (!frontResultQueue.positionString().equals("(0,0)")) {
            backEverythingQueue = (Item) everythingQueue.peekBack();

            if (frontResultQueue.positionString().equals(backEverythingQueue.positionString())) {
                backEverythingQueue = (Item) everythingQueue.dequeueBack();
            } else if (frontResultQueue.getRow() == backEverythingQueue.getRow()) {
                if (frontResultQueue.getColumn() == backEverythingQueue.getColumn() + 1 || frontResultQueue.getColumn() == backEverythingQueue.getColumn() - 1) {
                    //System.out.println("this are adjacent, switching from everything queue to result queue");
                    resultQueue.enqueueFront((Item) everythingQueue.dequeueBack());
                } else {
                    everythingQueue.dequeueBack();
                }
            } else if (frontResultQueue.getColumn() == backEverythingQueue.getColumn()) {
                if (frontResultQueue.getRow() == backEverythingQueue.getRow() + 1 || frontResultQueue.getRow() == backEverythingQueue.getRow() - 1) {
                    //System.out.println("this are adjacent, switching from everything queue to result queue");
                    resultQueue.enqueueFront((Item) everythingQueue.dequeueBack());
                } else {
                    everythingQueue.dequeueBack();
                }
            } else {
                everythingQueue.dequeueBack();
            }

            frontResultQueue = (Item) resultQueue.peekFront();
           // System.out.println(frontResultQueue.positionString());
        }

        /*
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count - 1);

         */

        int size = resultQueue.getSize();
        for (int i = 0; i < size; i++) {
            Item temp = (Item) resultQueue.dequeueFront();
            toReturn = toReturn + temp.positionString();
        }

        toReturn = toReturn + " " + steps;


        return toReturn;
    }

    private int[] findStartingLocation (char[][] map) {
        int[] toReturn = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == END) {
                    toReturn[0] = i;
                    toReturn[1] = j;
                    return toReturn;
                }
            }
        }
        return null;
    }



    public static void main(String[] args) throws Exception {

        char[][] map = toMap(
                    "$#########\n" +
                        "........##\n" +
                        "#####..#.#\n" +
                        "#.......##\n" +
                        "##.#.#%###\n" +
                        "##########");

        char[][] map5 = toMap(
                "$.#############################\n" +
                        "..................#..#........#\n" +
                        "#######..####..####..#..####..#\n" +
                        "#........#..#...........#.....#\n" +
                        "#..#######..####..####..#..#..#\n" +
                        "#..#.....#...........#..#..#..#\n" +
                        "#..#..#..####..#############..#\n" +
                        "#..#..#..#.....#..............#\n" +
                        "#..####..#..##########..#######\n" +
                        "#.....#...........#..#........#\n" +
                        "#############..####..####..####\n" +
                        "#........#..............#..#..#\n" +
                        "#######..#..####..####..#..#..#\n" +
                        "#...........#........#..#.....#\n" +
                        "####..####..#############..####\n" +
                        "#.....#.....#.....#.....#..#..#\n" +
                        "####..#######..#######..#..#..#\n" +
                        "#..............#..............#\n" +
                        "#..####..##########..#..#######\n" +
                        "#..#........#........#.........\n" +
                        "############################.%.");

        Maze test = new Maze();
        System.out.println("stack");
        System.out.println(test.solveWithStack(map));
        System.out.println("");
        System.out.println("queue");
        System.out.println(test.solveWithQueue(map));

    }
    private static char[][] toMap(String str) {
        String[] lines = str.split("\n");
        char[][] map = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            map[i] = lines[i].toCharArray();
        }

        return map;
    }




}
