
//See Instrucitons:
// http://apcsa-book.ausdk12.org/apcsa/r/cur/c4/L23_2D_arrays/exercises3.html?topic=c4%2FL23_2D_arrays.topic
public class ArrayRotation{
    private int[][] nums;
    
    public ArrayRotation(int n){
        nums = new int[n][n];
        int y=0;
        for(int r=0; r<nums.length; r++){
            for(int c=0; c<nums[0].length; c++){
                nums[r][c]= ++y;
            }
        }

    }


    public void rotate(){
        int[][] temp = new int[nums.length][nums[0].length];
        int y=0;
        for(int c=(nums[0].length-1); c>=0; c--){
            for(int r=0; r<nums.length; r++){
                temp[r][c]= ++y;

            }
        }
        nums=temp;

    }
    public void print(){
         for(int r=0; r<nums.length; r++){
            for(int c=0; c<nums[0].length; c++){
               System.out.print(nums[r][c] + " ");
            }
            System.out.println(" ");
        }

    }
    public static void main(String[] args) {
        ArrayRotation a = new ArrayRotation(3);
        a.rotate();
        a.print();
    }
}