package A1Q2;

/**
 * Represents an integer integral image, which allows the user to query the mean
 * value of an arbitrary rectangular subimage in O(1) time.  Uses O(n) memory,
 * where n is the number of pixels in the image.
 *
 * @author jameselder
 */
public class IntegralImage {

    private final int[][] integralImage;
    private final int imageHeight; // height of image (first index)
    private final int imageWidth; // width of image (second index)

    /**
     * Constructs an integral image from the given input image.  
     *
     * @author jameselder
     * @param image The image represented
     * @throws InvalidImageException Thrown if input array is not rectangular
     */
    public IntegralImage(int[][] image) throws InvalidImageException {
    	// create local variables
        this.imageHeight = image.length;
        int startValue = image[imageHeight-1].length;
        int finalState;
        // check if the images is constructed correctly with appropriate width and height
        if(imageHeight > 0){
            this.imageWidth = startValue;     
        }
        else{
        	imageWidth = 0;
        }
        
        // let pass the width an height to the integral image
        integralImage = new int[imageHeight][imageWidth];
        //first loop through the height variables
        for(int y = 0; y < imageHeight; y++ ){
        	// if the height is 0 or the individual lengths are not proportional to the specified width
        	// throw exception
        	if(imageHeight == 0|| imageWidth == 0 || image[y].length != imageWidth){
        		throw new InvalidImageException();
        	}
        	// Now we go through and add the value of each previuos cell and add store the value in the next
        	//plus the value in that cell.
        	for (int x= 0; x < imageWidth; x++) {
				finalState = image[y][x];
				if (x > 0) {
					finalState += integralImage[y][x-1];
				}
				
				if (y > 0) {
					finalState += integralImage[y-1][x];
				}
				if (y > 0 && x > 0) {
					finalState -= integralImage[y - 1][x - 1];
				}
				integralImage[y][x] = finalState;
				
			}
    }
             
    }

    /**
     * Returns the mean value of the rectangular sub-image specified by the
     * top, bottom, left and right parameters. The sub-image should include
     * pixels in rows top and bottom and columns left and right.  For example,
     * top = 1, bottom = 2, left = 1, right = 2 specifies a 2 x 2 sub-image starting
     * at (top, left) coordinate (1, 1).  
     *
     * @author jameselder
     * @param top top row of sub-image
     * @param bottom bottom row of sub-image
     * @param left left column of sub-image
     * @param right right column of sub-image
     * @return 
     * @throws BoundaryViolationException if image indices are out of range
     * @throws NullSubImageException if top > bottom or left > right
     */
    public double meanSubImage(int top, int bottom, int left, int right) throws BoundaryViolationException, NullSubImageException {
    	double finalValue= 0;
        if (top < 0 || left < 0) {
        	throw new BoundaryViolationException();
        }
        // going beyond bounds throw exception
    	if (top > bottom || left > right) {
        	throw new NullSubImageException();
        	// else compute the  the required value
        } else {
        		//set the final value to be the value in each cell
        	 finalValue = integralImage[bottom][right];
        	if (top > 0) {
        		finalValue -= integralImage[top - 1][right];
        	}
        	if (left > 0) {
        		finalValue -= integralImage[bottom][left - 1];
        	}
        	if (top > 0 && left > 0) {
        		finalValue += integralImage[top - 1][left - 1];
        	}
        	finalValue /= (bottom - top + 1) * (right - left + 1);
        	
        	return finalValue;
        }
      }
}
