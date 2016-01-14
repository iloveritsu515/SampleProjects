/*  Christian White
    masc0339
*/

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
public class OrderedVector <E> implements OrderedListADT <E> {
    private int currentSize, maxSize;
    private E[] storage;
    public static final int DEFAULT_MAX_SIZE = 100;

    public OrderedVector(){
    	currentSize = 0;
	maxSize = DEFAULT_MAX_SIZE;
	storage = (E[])new Object[maxSize];
    }
//  Adds the Object obj to the list in the correct position as determined by the Comparable interface.
    public void insert(E obj){
    if(currentSize == maxSize){
       maxSize *= 2;   //doubles the size of the array
       E[]newStorage = (E[])new Object[maxSize];  //makes new array object newStorage
       for(int j=0; j < currentSize; j++){ //following lines copy all objects from storage into newStorage
         newStorage[j] = storage[j];
	 }
	 storage = newStorage;
    }
    int insert = binSearchInsert(obj, 0, currentSize-1); //calls binSearhInsert method
    for(int i = currentSize-1; i >= insert; i--){
       storage[i + 1] = storage[i]; //moves object to the right starting from the last object
       }
       storage[insert] = obj;
       currentSize++;
     }
//  Removes the object located at the parameter index position (zero based).
//  Throws IndexOutOfBoundsException if the index does not map to a valid position within the list.
    public E remove(int index){
    if(index > currentSize || index < 0)
    //if the given index position is greater than the currentsize or a negative number give error
       throw new IndexOutOfBoundsException();
    if(currentSize <= (maxSize/4)){ //if array is 25% or less full reduce size of the array by half
       maxSize/=2;
       E[]newStorage = (E[]) new Object [maxSize];
       for(int j = 0; j < currentSize; j++){  //copy objects into new array
          newStorage[j] = storage[j];
	 }
	 storage = newStorage;
    }
    E obj = storage[index];
    for(int i =(index + 1); i < currentSize; i++)
       storage[i - 1] = storage[i]; //moves all object over starting from the next index until the end
    currentSize--;
    return obj;
    }
//  Removes and returns the parameter object obj from the list if the list contains it, null otherwise.
    public E remove(E obj){
    if(currentSize <= (maxSize/4)){
       maxSize/=2;
       E[]newStorage = (E[]) new Object [maxSize];
       for(int j = 0; j < currentSize; j++){
          newStorage[j] = storage[j];
	 }
	 storage = newStorage;
    }
    obj = null;
    int insert = binSearch(obj, 0, currentSize-1);
    if (insert <= currentSize-1 && insert >= 0){
       obj = storage[insert];
       for(int i = insert; i < currentSize-1; i++){
         storage[i] = storage[i + 1];
         currentSize--;
       }
       }
      return obj;
    }
//  Returns the parameter object located at the parameter index position (zero based).
//  Throws IndexOutOfBoundsException if the index does not map to a valid position within the underlying array
    public E get(int index){
    if(index > currentSize || index < 0)
       throw new IndexOutOfBoundsException();
     E obj = storage[index];
    return obj;
    }
//  Returns the parameter object obj from the list if the list contains it, null otherwise.
    public E get(E obj){
    int insert = binSearch(obj, 0, currentSize-1);
    if(insert == -1)
       return null;
    return obj;
    }
//  Returns true if the parameter object obj is in the list, false otherwise.
    public boolean contains(E obj){
    int insert = binSearch(obj, 0, currentSize-1);
    if (insert == -1)
       return false;
    return true;
    }
//  The list is returned to an empty state.
    public void clear(){
    currentSize = 0;
    }
//    Returns true if the list is empty, otherwise false
    public boolean isEmpty(){
    if(currentSize == 0)
       return true;
    return false;
    }
//  Returns the number of Objects currently in the list.
    public int size(){
    return currentSize;
    }
//  Returns an Iterator of the values in the list, presented in
//  the same order as the list.
    public Iterator<E> iterator(){
            return new IteratorHelper();
        }  //this code was taken from the sample programs on pindar.sdsu.edu
        class IteratorHelper implements Iterator<E> {
        int iterIndex;
        public IteratorHelper() {
        iterIndex = 0;
        }
        public boolean hasNext() {
        return iterIndex < currentSize;
        }
        public E next() {
        if(!hasNext())
            throw new NoSuchElementException();
        return storage[iterIndex++];
        }
        public void remove() {
        throw new UnsupportedOperationException();
        }
    }
private int binSearchInsert(E obj, int lo, int hi){
if (hi < lo) return lo;//object found
int mid = (lo + hi)/2;//go to next half point
int comp = ((Comparable<E>)obj).compareTo(storage[mid]);
if(comp < 0)
   return binSearchInsert(obj, lo, mid-1);
return binSearchInsert(obj, mid+1, hi);
}
private int binSearch(E obj, int lo, int hi) {
if (hi < lo) return -1; //not found
int mid = (lo + hi)/2;
int comp = ((Comparable<E>)obj).compareTo(storage[mid]);
if(comp == 0) return mid;//found
if(comp < 0)
   return binSearch(obj, lo, mid-1);//go left
return binSearch(obj, mid+1, hi);//go right
}
}
