/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package radixsort;
import javax.swing.JOptionPane;


/**
 *
 * @author glSon8840
 */
public class RadixSort {

    /**
     * @param args the command line arguments
     */
    // Input section to determine the size of the array
    static int input;
    // Input to determine the maximum size of one of the numbers in the array
    static int maxRando;
    static int[] array = new int[input];
    
    public static void main(String[] args) {
        //Fills array with random numbers
        for (int i = 0; i < input; i++)
        {
            array[i] = random(0, maxRando);
        }
        System.out.println("Start!");
        
        //Gets system time in nanoseconds
        double startTime = System.nanoTime();
        
        radixSort(array);
        
        //Prints out time to sort (in milliseconds)
        System.out.println("Sort time: " + ((System.nanoTime() - startTime) / 1000000) + "ms");
        
        //Prints sorted array
        System.out.print("\nSorted Array: ");
        for (int i = 0; i < input; i++)
        {
            if(i != 0) System.out.print(", ");
            if (i % 20 == 0) System.out.println();
            System.out.print(array[i]);
        }
        System.out.println();
    }
    
    public static void radixSort(int[] array)
    {
        int largestValue = getLargestValue(array);
        
        for (int exponent = 1; largestValue / exponent > 0; exponent *= 10)
        {
            int[] sortedArray = new int[input], numberCount = new int[10];
            
            //Gets number of digits with the same value (number of 0's, 1's, ...)
            for (int i = 0; i < input; i++)
                numberCount[getDigit(array[i], exponent)]++;
            
            //Get's real position of numbers in sortedArray 
            for (int i = 1; i < 10; i++)
                numberCount[i] += numberCount[i - 1];
            
            //Writes to sortedArray (in reverse order because position in numberOfNumbers is the furthest position of that digit
            for(int i = input - 1; i >= 0; i--)
            {
                sortedArray[numberCount[getDigit(array[i], exponent)] - 1] = array[i];
                numberCount[getDigit(array[i], exponent)]--;
            }
            
            //Updates array with sorted digits
            for (int i = 0; i < input; i++)
                array[i] = sortedArray[i];
        }
    }
    
    public static int getDigit(int number, int digitNumber)
    {
        return (int) (number / digitNumber) % 10;
    }
    
    public static int getLargestValue(int[] array)
    {
        int largestValue = 0;
        for (int element : array)
        {
            largestValue = element > largestValue ? element : largestValue;
        }
        return largestValue;
    }
    
    public static int random(int min, int max)
    {
        return (int)(Math.random() * (max + 1) + min);
    }
    
}
