#include <iostream>
#include <conio.h>
using namespace std;

	//declaring the sudoku table as an array
	int table[9][9];

	void printSudoku() {
		//for each column and row print out seperators and lines for formatting
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cout << table[i][j] << " | ";
			}
			cout << endl;
			for (int j = 0; j < 35; j++) {
				cout << "-";
			}
			cout << endl;
		}
	}//printSudoku

	int rowCheck(int r, int c, int num) {
		for (int i = 0; i < 9; i++) {
			if (i == c && i < 8){
				i++;
			}
			if (i == c && c == 8){
				break;
			}
			if (table[r][i] == num){
				return 0;
			}
		}
		return 1;
	}//rowCheck

	int colCheck(int r, int c, int num) {
		for (int i = 0; i < 9; i++) {
			if (i == r && i < 8){
				i++;
			}
			if (i == r && i == 8){
				break;
			}
			if (table[i][c] == num) {
				return 0;
			}
		}
		return 1;
	}//colCheck

	/*rmod and cmod are passed as i-(i%3) where i is incremented by 1:
	   1 - (1 % 3) = 0 | 2 - (2 % 3) = 0 | 3 - (3 % 3) = 0
	   3 - (3 % 3) = 3 | 4 - (4 % 3) = 3 | 5 - (5 % 3) = 3
	   6 - (6 % 3) = 6 | 7 - (7 % 3) = 6 | 8 - (8 % 3) = 6
	   This gives the starting point for each 3x3 panel             */
	int panelCheck(int r, int c, int rmod, int cmod, int num) {
		for (int threeTimes = 0; threeTimes < 3; threeTimes++){
			for (int i = cmod; i < cmod + 3; i++){
				if (i == c && i < 8) {
					i++;
				}

				if (i >= cmod + 3) {
					break;
				}

				if (i == c && i == 8) {
					break;
				}

				if (table[rmod][i] == num) {
					return 0;
				}
			}
			rmod = rmod + 1;
		}
		return 1;
	}//panelCheck

	int countThrough(int r, int c) {
		while (r < 9 && table[r][c] != 0) {
			c++;
			if (c == 9) {
				r++;
				c = 0;
			}
		}

		if (r == 9) {
			return 1;
		}

		for (int i = 1; i <= 9; i++) {
			table[r][c] = i;
			if (rowCheck(r, c, i) == 1 && colCheck(r, c, i) == 1 && panelCheck(r, c, r - r % 3, c - c % 3, i) == 1 && countThrough(r, c) == 1){
				table[r][c] = i;
				return 1;
			}
		}
		table[r][c] = 0;
		return 0;
	}//countThrough

	int solve() {
		return countThrough(0, 0);
	}//solve

	int main(){
		int chart[9][9] = {
				{ 0, 1, 2, 0, 0, 0, 0, 7, 9 },
				{ 0, 0, 9, 8, 3, 0, 0, 4, 0 },
				{ 5, 0, 0, 0, 0, 7, 0, 0, 8 },
				{ 1, 0, 0, 0, 6, 5, 9, 0, 3 },
				{ 0, 0, 5, 3, 7, 0, 1, 0, 0 },
				{ 0, 3, 0, 0, 0, 0, 2, 0, 0 },
				{ 0, 0, 0, 5, 9, 4, 0, 0, 0 },
				{ 9, 2, 0, 0, 8, 0, 0, 0, 0 },
				{ 4, 0, 0, 0, 0, 3, 0, 0, 6 }
		};

		//filling up the public table array
		//declaring this array and filling it produces an error, return to in future
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				table[i][j] = chart[i][j];
			}
		}

		solve();
		printSudoku();
		_getch();
		return 0;
	}//main