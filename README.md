# Mathematical Operations using Stacks and Queues
Program that will act as a simple calculator that supports addition, subtraction, multiplication and division operators. The program operates by taking in a pair of parameters (operation and number) that are separated by a space. For example a valid entry could consist of a + followed by 4.4. This would evaluate to 4.4. If the operation and value pair "* 2" in now entered the result would evaluate to 8.8. The program will store all of the operations on a Queue. In addition to storing the operator and operand on the Queue, you must also be able to store Undo and Redo operations. These two operations will be represented by the letter 'U' for undo and the letter 'R' for redo. To evaluate all of the operations on the Queue the letter 'E' will be used. Once evaluated the Queue will be empty and ready to start a new calculation. To exit the program the letter 'X' will be used. On exit the contents of the Queue should be evaluated one last time.

# List in order (fastest to slowest) your selection of algorithm to use when the sort contain 25 elements.  
As per my obervation on sorting 25 elements through different algorithms 
  - My preference in selecting the algorithm for sorting is as follows: Quick Sort, Insertion Sort, Merge Sort, Bubble Sort, Selection Sort
  
  Reason behind giving this preference is it takes less compares to sort algortihms and less time in execution.
        
        //o	List in order (fastest to slowest) your selection of algorithm to use when the sort contain 25000 elements.
        //-     As per my obeservation on sorting 25000 elements through different algorithms
        //              - I will use Merge Sort, Quick Sort, Bubble Sort, Insertion Sort, Selection Sort
        
        //o	List the algorithm and the BIG O notation for that algorithm. 
        //          - Quick Sort        = O(n log(n))
        //          - Merge Sort        = O(n log(n))
        //          - Bubble Sort       = O(n)
        //          - Insertion Sort    = O(n)
        //          - Selection Sort    = O(n^2)
        
        //o	Which algorithm has the best performance of the basic step?  Does this have any impact on your selection of fastest algorithm 
        //      when sorting 25000 elements.
        //          - Best Performance in sorting the most basic array was Quick Sort.
        //          - While selecting algorithm for sorting 25000 elements the algorithm with lesser comparison and less execution speed is 
        //              preferred where quick Sort laid behind by Merge Sort in both terms
