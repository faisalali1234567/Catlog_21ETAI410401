# Shamir's Secret Sharing Algorithm

## Overview
This project is a basic implementation of Shamir's Secret Sharing algorithm. It uses Lagrange interpolation to reconstruct a secret from shared parts provided in JSON files.

## Project Structure
- `src/SecretSharing.java`: Main Java program that processes the data.
- `testcases/testcase1.json`: First example test case.
- `testcases/testcase2.json`: Second example test case.
- `lib/gson-2.8.8.jar`: Gson library needed for parsing JSON files.

## How It Works
1. The program reads input from JSON files, which contain data points for a polynomial.
2. It decodes the data and applies Lagrange interpolation to find the constant term, which is the secret.
3. The result is printed as the output.

## Test Case Format
Each test case contains:
- `n`: Total number of points.
- `k`: Minimum points needed to reconstruct the secret.
- The x and y values of the points, where y is encoded in a specified base.

## Output
The program will print the secret (constant term) calculated from each test case.

![image](https://github.com/user-attachments/assets/b515dc17-44db-47dc-b1ab-f20f4c4fe562)
