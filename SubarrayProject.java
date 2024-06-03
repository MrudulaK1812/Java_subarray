import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SubarrayProject extends Application {

    private TextArea arrayInput;
    private TextArea output;
    private TextArea codeOutput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Subarray");
        BorderPane root = new BorderPane();
        root.setPrefHeight(20);
        // Image image = new
        // Image("file:/C:/Users/Admin/JavaFx/Window/Sample/src/krishna.jpg");
        // Scale the original image to a larger size
        /*
         * Add background image
         * Image backgroundImage = new
         * Image("C:/Users/mrudu/OneDrive/Desktop/website image'.webp/");
         * BackgroundImage background = new BackgroundImage(
         * backgroundImage,
         * BackgroundRepeat.NO_REPEAT,
         * BackgroundRepeat.NO_REPEAT,
         * BackgroundPosition.CENTER,
         * new BackgroundSize(100, 100, true, true, true, true));
         * root.setBackground(new Background(background));
         */

        Image largerIcon = new Image("file:/C:/Users/Admin/JavaFx/Window/Sample/src/krishna.jpg", 100, 100, true, true); // You
                                                                                                                         // can
                                                                                                                         // adjust
                                                                                                                         // the
                                                                                                                         // size
                                                                                                                         // as
                                                                                                                         // needed
        primaryStage.getIcons().add(largerIcon);
        // Create an ImageView to display the image
        // ImageView imageView = new ImageView(image);
        // Add the ImageView to the center of the BorderPane
        // root.setCenter(imageView);

        // Create the array input text area
        arrayInput = new TextArea();
        arrayInput.setPrefWidth(50);
        arrayInput.setPrefHeight(50);
        arrayInput.setPromptText("Enter an array of integers, separated by spaces:");
        root.setTop(arrayInput);

        // Create the output text area
        output = new TextArea();
        output.setEditable(false);
        root.setCenter(output);

        // Create the code output text area
        codeOutput = new TextArea();
        codeOutput.setEditable(false);
        codeOutput.setPrefHeight(0);
        root.setBottom(codeOutput);

        // Create the print all subarrays button
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: #FFDAB9;");
        root.setLeft(vbox);
        vbox.setSpacing(40);
        vbox.setPrefHeight(2000);

        Button button = new Button("Click Here to print all subarrays");
        button.setStyle("-fx-background-color: lightblue;");
        button.setPrefWidth(200);
        Button button2 = new Button("click Here to print max Sum");
        button2.setStyle("-fx-background-color: lightblue;");
        Button button3 = new Button("print maximum sum subarray");
        button3.setStyle("-fx-background-color: lightblue;");
        Button button4 = new Button("print subarray");
        button4.setStyle("-fx-background-color: lightblue;");

        vbox.getChildren().addAll(button, button2, button3, button4);

        button.setOnAction(e -> {
            // Get the array from the user input
            String[] arrayStrings = arrayInput.getText().split(" ");
            int[] array = new int[arrayStrings.length];
            for (int i = 0; i < arrayStrings.length; i++) {
                array[i] = Integer.parseInt(arrayStrings[i]);
            }

            // Print all subarrays of the array
            StringBuilder outputStringBuilder = new StringBuilder();
            outputStringBuilder.append("All subarrays of the array:\n");
            for (int i = 0; i < array.length; i++) {
                for (int j = i; j < array.length; j++) {
                    outputStringBuilder.append("[");
                    for (int k = i; k <= j; k++) {
                        outputStringBuilder.append(array[k] + ", ");
                    }
                    outputStringBuilder.append("]\n");
                }
            }

            // Set the output text area text
            output.setText(outputStringBuilder.toString());
        });

        // Create the print maximum subarray sum button

        button2.setOnAction(e -> {
            // Get the array from the user input
            String[] arrayStrings = arrayInput.getText().split(" ");
            int[] array = new int[arrayStrings.length];
            for (int i = 0; i < arrayStrings.length; i++) {
                array[i] = Integer.parseInt(arrayStrings[i]);
            }

            // Find the maximum subarray sum
            int maximumSubarraySum = 0;
            int currentSubarraySum = 0;
            for (int i = 0; i < array.length; i++) {
                currentSubarraySum += array[i];
                if (currentSubarraySum < 0) {
                    currentSubarraySum = 0;
                }
                if (maximumSubarraySum < currentSubarraySum) {
                    maximumSubarraySum = currentSubarraySum;
                }
            }

            // Set the output text area text
            output.setText("The maximum subarray sum is: " + maximumSubarraySum);

        });
        button3.setOnAction(e -> {
            // Get the array from the user input
            String[] arrayStrings = arrayInput.getText().split(" ");
            int[] array = new int[arrayStrings.length];
            StringBuilder outputStringBuilder = new StringBuilder();
            for (int i = 0; i < arrayStrings.length; i++) {
                array[i] = Integer.parseInt(arrayStrings[i]);
            }

            // Find the maximum sum subarray
            int start = 0;
            int end = 0;
            int sum = 0;
            int max_Sum = Integer.MIN_VALUE;
            int x = 0;
            for (int i = 0; i < array.length; i++) {
                if (sum == 0)
                    x = i;

                sum = sum + array[i];
                if (sum > max_Sum) {
                    start = x;
                    end = i;
                    max_Sum = sum;
                }
                if (sum < 0)
                    sum = 0;
            }
            for (int i = start; i <= end; i++) {
                outputStringBuilder.append(array[i] + ", ");
            }
            output.setText(outputStringBuilder.toString());

        });
        button4.setOnAction(e -> showComplexityDialog());

        primaryStage.setScene(new Scene(root, 1000, 500));
        root.setPadding(new Insets(50));
        primaryStage.show();

    }

    private void showComplexityDialog() {
        List<String> choices = new ArrayList<>();
        choices.add("N^2");
        choices.add("N^3");
        choices.add("N");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("N", choices);
        dialog.setTitle("Complexity Selection");
        dialog.setHeaderText("Select the complexity for printing subarrays:");
        dialog.setContentText("Complexity:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(complexity -> {
            String subarrayCode = generateSubarrayCode(complexity);

            Stage subarrayStage = new Stage();
            subarrayStage.setTitle("Subarray Printing");

            TextArea subarrayTextArea = new TextArea(subarrayCode);
            subarrayTextArea.setEditable(false);
            subarrayTextArea.setPrefHeight(500);

            VBox subarrayLayout = new VBox(subarrayTextArea);
            Scene subarrayScene = new Scene(subarrayLayout, 700, 500);

            subarrayStage.setScene(subarrayScene);
            subarrayStage.show();
        });
    }

    private String generateSubarrayCode(String complexity) {
        // Implement code generation logic based on the selected complexity
        // Replace this with your actual code generation logic
        String code = "int start = 0;\n int end = 0;\nint sum = 0;\nint max_Sum = Integer.MIN_VALUE;\nint x = 0;";
        return code;
    }

    public static void main(String[] args) throws Exception {

        launch(args);
    }
}