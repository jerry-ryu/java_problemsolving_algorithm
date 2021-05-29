package dynamic_algorithm;

public class Zero_One_Knapsack {

	static int find_Knapsack(int[] weight, int[] value, int limit) {

		int[] table = new int[limit+1];

		table[0] = 0;
		for (int i = 0; i < weight.length; i++) {
			if(weight[i]<= limit) {
				table[weight[i]] = value[i];
			}
		}

		for (int i = 1; i <= limit; i++) {
			
			for (int j = 0; j < i; j++) {
				if(j!= i-j) {
					table[i] = Math.max(table[i], table[j] + table[i-j]);
				}
			}
		}

		return table[limit];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] weight = { 6, 4, 3, 5 };
		int[] value = { 13, 8, 6, 12 };
		
		System.out.println(find_Knapsack(weight, value, 19));
		}
}
