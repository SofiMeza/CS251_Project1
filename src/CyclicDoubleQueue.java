/**
 * CS 251: Data Structures and Algorithms
 * Project 1: Part 1
 *
 * TODO: Complete CyclicDoubleQueue.
 *
 * @author , Sofia Meza Robles
 * @username , meza8
 * @sources TODO: list your sources here
 *
 *
 *
 */

@SuppressWarnings("unchecked")
public class CyclicDoubleQueue<Item> {

    // initial capacity of the queue
    private int initialCapacity = 7;

    // queue (array of items)
    private Item[] queue;

    // front of the queue
    private int front;

    // back of the queue
    private int back;

    // keeps track of the size;
    private int size;

    // increase factor for resizing the queue
    private int increaseFactor = 2;

    // decrease factor for resizing the queue
    private double decreaseFactor = 0.50;


    /**
     * Constructor of the class.
     * TODO: complete the default Constructor of the class
     *
     * initial capacity = 7;
     *
     */
    @SuppressWarnings("unchecked")
    public CyclicDoubleQueue() {
        this.queue = (Item[]) new Object[this.initialCapacity];
        this.front = 0;
        this.back = 0;
        this.size = 0;
    }

    /**
     * Constructor of the class.
     * TODO: complete the default Constructor of the class
     *
     * initial capacity = 7;
     *
     */
    @SuppressWarnings("unchecked")
    public CyclicDoubleQueue(int initialCapacity, int increaseFactor, double decreaseFactor) {
        this.initialCapacity = initialCapacity;
        this.increaseFactor = increaseFactor;
        this.decreaseFactor = decreaseFactor;
        this.queue = (Item[]) new Object[this.initialCapacity];
        this.front = 0;
        this.back = 0;
        this.size = 0;
    }

    public int getFront() {
        return this.front;
    }

    public int getBack() {
        return this.back;
    }

    public int getSize() {
        return this.size;
    }

    public int getQueueLength() {
        return this.queue.length;
    }

    /**
     *
     * TODO: Enqueue the passed item to the front of the queue.
     *
     */
    public void enqueueFront(Item item) {
        if (isFull()) {
            increaseArraySize();
        }

        if (isEmpty()) {
            this.front = 0;
            this.back = 0;
        }else if (this.front == 0) {
            this.front = this.getQueueLength() - 1;
        } else {
            this.front = this.front -1;
        }

        this.queue[this.front] = item;

        this.size++;
    }

    /**
     *
     * TODO: dequeue the element at the front of the queue
     *
     */
    public Item dequeueFront() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }

        Item toReturn = this.queue[this.front];

        this.queue[this.front] = null;

        if (this.front == this.back) {
            this.front = 0;
            this.back = 0;
        } else if (this.front == this.getQueueLength() - 1) {
            this.front = 0;
        } else {
            this.front++;
        }

        this.size--;

        if (this.size <= this.getQueueLength()/4 && this.getQueueLength() > this.initialCapacity) {
            decreaseArraySize();
        }

        return toReturn;
    }

    /**
     *
     * TODO: Enqueue the passed item to the back of the queue.
     *
     */
    public void enqueueBack(Item item) {
        if (isFull()) {
            increaseArraySize();
        }

        if (isEmpty()) {
            this.front = 0;
            this.back = 0;
            //this.firstBackInsert = false;
        } else if (this.back == this.getQueueLength() - 1) {
            this.back = 0;
        } else {
            this.back = this.back + 1;
        }

        this.queue[this.back] = item;
        this.size++;
    }

    /**
     *
     * TODO: dequeue the element at the back of the queue
     *
     */
    public Item dequeueBack() throws Exception  {
      if (isEmpty()) {
          throw new Exception();
      }
      Item toReturn = this.queue[this.back];
      this.queue[this.back] = null;


      if (this.front == this.back) {
          this.front = 0;
          this.back = 0;
      } else if (this.back == 0) {
          this.back = this.getQueueLength() -1;
      } else {
          this.back = this.back -1;
      }

      this.size--;

      if (this.size <= this.getQueueLength()/4 && this.getQueueLength() > this.initialCapacity) {
          decreaseArraySize();
      }
      return toReturn;
    }

    /**
     *
     * TODO: peek the element at the front of the queue
     *
     */
    public Item peekFront() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return this.queue[this.front];
    }

    /**
     *
     * TODO: peek the element at the back of the queue
     *
     */
    public Item peekBack() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return this.queue[this.back];
    }

    /**
     *
     * TODO: check if the queue is empty
     *
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     *
     * TODO: check if the queue is full
     *
     */
    public boolean isFull() {
        return (this.size == this.getQueueLength());
    }

    /**
     *
     * TODO: Increase the size of the array by the factor
     *
     */
    private void increaseArraySize() {
        Item[] tempArray = (Item[]) new Object[this.increaseFactor * this.getQueueLength()];

        for (int i = 0; i < getQueueLength(); i++) {
            if (this.front == this.getQueueLength() - 1) {
                tempArray[i] = this.queue[this.front];
                this.front = 0;
            } else {
                tempArray[i] = this.queue[this.front];
                this.front++;
            }
        }

        this.front = 0;
        this.back = this.size - 1;
        //this.getQueueLength() = this.increaseFactor * this.getQueueLength();
        this.queue = tempArray;
    }

    /**
     *
     * TODO: Decrease the size of the array by the factor
     *
     */
    private void decreaseArraySize() throws Exception {
        double newSize = this.decreaseFactor * this.getQueueLength();
        Item[] tempArray = (Item[]) new Object[ (int) newSize];

        for (int i = 0; i < this.size; i++) {
            if (this.front == this.getQueueLength() - 1) {
                tempArray[i] = this.queue[this.front];
                this.front = 0;
            } else {
                tempArray[i] = this.queue[this.front];
                this.front++;
            }
        }

        this.front = 0;
        this.back = this.size - 1;
        //this.getQueueLength() = (int) newSize;
        this.queue = tempArray;
    }
}
