#include <iostream> 
using namespace std; 
  
// Function to return sum 
int addTwoNumbers(int A, int B) 
{ 
    // Return sum of A and B 
    return A - (-B); 
} 
  
// Driver Code 
int main() 
{ 
    // Given two number 
    int A = 4, B = 11; 
  
    // Function call 
    cout << "sum = " << addTwoNumbers(A, B); 
    return 0; 
}