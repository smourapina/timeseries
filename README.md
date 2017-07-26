# Time series processing

This application has as purpose to process time series data, calculating a set of statistics.
It receives as input the location for a file containing the data in plain text (comprised of a timestamp and the price ratio measurement at this time), and prints as a result into the console the following analytics:
- T — number of seconds since beginning of epoch at which rolling window ends.
- V — measurement of price ratio at time T.
- N — number of measurements in the window.
- RS — a rolling sum of measurements in the window.
- MinV — minimum price ratio in the window.
- MaxV — maximum price ratio in the window.

### Assumptions made about the task
1. Input validation is not done in this application for the location of the input file. It is assumed as a simple value (string should be inputted without quotes), but proper path validation should be done instead (emitting an error when the file is not found in the location).
2. Ideally, tests for point 1. should also be added, as well as additional testing for edge cases. A property-based testing approach would be beneficial.
3. The rolling window length is assumed to be constant (and equal to 60 seconds), but different lengths should ideally be considered.
4. Calculation results are being accumulated in a `StringBuilder` which is part of `TimeSeriesAnalysis`. This solution is not ideal because we might want to use the results for further purposes (than printing results). An improvement that could be made would be to set it to return a `Stream[TimeSeriesStats]`, for example.

### Running the project
Navigate to the directory of the project and then run:
```
sbt run
```
You will be prompted to enter the location of the file. This needs to be inputted without quotes.
Next, the results of the analysis will be printed into the console. A sample file can be found under the `data` directory of this project.

An example of the usage is presented here:
```
Please enter the location of the input file: data/data_scala.txt

T 		   V 	   N  RS 	 MinV 	 MaxV 
--------------------------------------------
1355270609 1.80215 1 1.80215 1.80215 1.80215 
1355270621 1.80185 2 3.60400 1.80185 1.80215 
1355270646 1.80195 3 5.40595 1.80185 1.80215 
1355270702 1.80225 2 3.60420 1.80195 1.80225 
1355270702 1.80215 3 5.40635 1.80195 1.80225 
1355270829 1.80235 1 1.80235 1.80235 1.80235 
1355270854 1.80205 2 3.60440 1.80205 1.80235 
```
