package org.firstinspires.ftc.teamcode;

import org.opencv.core.*;
import org.opencv.imgproc.*;
import org.openftc.easyopencv.OpenCvPipeline;

public class SignalSleevePipeline extends OpenCvPipeline {

    //Outputs
    private final Mat blurOutput = new Mat();
    private final Mat zone3Output = new Mat(); // Blue
    private final Mat zone2Output = new Mat(); // Green
    private final Mat zone1Output = new Mat(); // Pink

    private int zone = 0;

    /**
     * This is the primary method that runs the entire pipeline and updates the outputs.
     */
    public Mat processFrame(Mat source0) {
        // Step Blur0:
        Mat blurInput = source0;
        double blurRadius = 19.0;
        blur(blurInput, blurRadius, blurOutput);

        // Step HSV_Threshold0:
        Mat zone3Input = blurOutput;
        double[] zone3Hue = {87, 105};
        double[] zone3Saturation = {94, 255};
        double[] zone3Value = {87, 255};
        hsvThreshold(zone3Input, zone3Hue, zone3Saturation, zone3Value, zone3Output);

        // Step HSV_Threshold1:
        Mat zone2Input = blurOutput;
        double[] zone2Hue = {56, 85};
        double[] zone2Saturation = {85, 145};
        double[] zone2Value = {50, 151};
        hsvThreshold(zone2Input, zone2Hue, zone2Saturation, zone2Value, zone2Output);

        // Step HSV_Threshold2:
        Mat zone1Input = blurOutput;
        double[] zone1Hue = {142, 172};
        double[] zone1Saturation = {0, 255};
        double[] zone1Value = {0, 255};
        hsvThreshold(zone1Input, zone1Hue, zone1Saturation, zone1Value, zone1Output);

        int zone1Count = Core.countNonZero(zone1Output);
        int zone2Count = Core.countNonZero(zone2Output);
        int zone3Count = Core.countNonZero(zone3Output);

        if (zone1Count > Math.max(zone2Count, zone3Count)) {
            zone = 1;
        } else if (zone3Count > Math.max(zone1Count, zone2Count)) {
            zone = 3;
        } else {
            zone = 2;
        }


        Imgproc.putText(
                source0,
                "Zone: " + zone,
                new Point(10, 20),
                Imgproc.FONT_HERSHEY_PLAIN, 1,
                new Scalar(255, 255, 255), 3);
        return zone2Output;
    }

    /**
     * Use this to determine where to park the robot
     */
    public int getZone() {
        return zone;
    }

    private void setZone(int z) {
        zone = z;
    }

    /**
     * Softens an image using one of several filters.
     *
     * @param input        The image on which to perform the blur.
     * @param doubleRadius The radius for the blur.
     * @param output       The image in which to store the output.
     */
    private void blur(Mat input, double doubleRadius, Mat output) {
        int radius = (int) (doubleRadius + 0.5);
        int kernelSize = 6 * radius + 1;
        Imgproc.GaussianBlur(input, output, new Size(kernelSize, kernelSize), radius);
    }

    /**
     * Segment an image based on hue, saturation, and value ranges.
     *
     * @param input The image on which to perform the HSL threshold.
     * @param hue   The min and max hue
     * @param sat   The min and max saturation
     * @param val   The min and max value
     */
    private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val, Mat out) {
        Imgproc.cvtColor(input, out, Imgproc.COLOR_RGB2HSV); // EasyOpenCV uses RGB instead of BGR
        Core.inRange(out, new Scalar(hue[0], sat[0], val[0]), new Scalar(hue[1], sat[1], val[1]), out);
    }

}


