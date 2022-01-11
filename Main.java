import java.util.*;
import java.lang.*;

class Main {
  public static void main(String[] args) {
		Random r = new Random();
		int arr16[] = new int[16];
		int arr32[] = new int[32];
		int arr64[] = new int[64];
		int arr128[] = new int[128];
		int arr256[] = new int[256];

		long timeAvg[] = new long[16];

		for(int h = 0; h < 100; h++){
			for(int i = 0; i < arr256.length; i++){
				arr256[i] = r.nextInt(1000) + 1; 
				if(i < 128){
					arr128[i] = r.nextInt(1000) + 1; 
				}
				if(i < 64){
					arr64[i] = r.nextInt(1000) + 1; 
				}
				if(i < 32){
					arr32[i] = r.nextInt(1000) + 1; 
				}
				if(i < 16){
					arr16[i] = r.nextInt(1000) + 1; 
				}
			}
		timeAvg[1] += insertionSort(arr16);
		timeAvg[2] += mergeSort(arr16);
		timeAvg[3] += quickSort(arr16);

		timeAvg[4] += insertionSort(arr32);
		timeAvg[5] += mergeSort(arr32);
		timeAvg[6] += quickSort(arr32);

		timeAvg[7] += insertionSort(arr64);
		timeAvg[8] += mergeSort(arr64);
		timeAvg[9] += quickSort(arr64);

		timeAvg[10] += insertionSort(arr128);
		timeAvg[11] += mergeSort(arr128);
		timeAvg[12] += quickSort(arr128);

		timeAvg[13] += insertionSort(arr256);
		timeAvg[14] += mergeSort(arr256);
		timeAvg[15] += quickSort(arr256);
		}
		int why = 4;
		for(int i = 1; i <= 15; i++){
			if((i + 3) % 3 == 1){
				int intWHY = (int) Math.pow(2,why);
				System.out.println("Size: " + intWHY);
				System.out.println("Insertion Sort AVG time: " + timeAvg[i]/100);
			}
			if((i + 3) % 3 == 2){
				System.out.println("Merge Sort AVG time: " + timeAvg[i]/100);
			}
			if((i + 3) % 3 == 0){
				System.out.println("Quick Sort AVG time : " + timeAvg[i]/100);
				why++;
				System.out.println("\n");
			}
		}
  }
	static long insertionSort(int[] arr){
		int arrCopy[] = new int[arr.length];
		arrCopy = arr.clone();
		long timeIS = System.nanoTime();
		int point, check; 
		for(int i = 1; i < arrCopy.length; i++){
			point = arrCopy[i];
			check = i - 1;
			while(check >= 0 && point < arrCopy[check]){
				int tmp = arrCopy[check + 1];
				arrCopy[check + 1] = arrCopy[check];
				arrCopy[check] = tmp;
				check--;
			}
		}
			return System.nanoTime() - timeIS; 
	}
	static long mergeSort(int[] arr){
		int arrCopy[] = new int[arr.length];
		arrCopy = arr.clone();

		long timeMS = System.nanoTime();
		sort(arrCopy, 0, arrCopy.length - 1);

		return System.nanoTime() - timeMS;
	}
	static void sort(int[] arrCopy, int first, int last ){
		if(first < last){
			int mid = (last - first)/2 + first;
			sort(arrCopy, first, mid);
			sort(arrCopy, mid + 1, last);
			merge(arrCopy, first, mid, last);
		}
	}
	static void merge(int arrCopy[], int first, int mid, int last){
		//works its just that 3rd parameter is non inclusive
		int leftCopy[] = Arrays.copyOfRange(arrCopy, first, mid + 1);
		int rightCopy[] = Arrays.copyOfRange(arrCopy, mid + 1, last + 1);
		int l = 0, r = 0, ac = first;
		while(l < leftCopy.length && r < rightCopy.length){
			if(leftCopy[l] <= rightCopy[r]){
				arrCopy[ac] = leftCopy[l];
				l++;
			}
			else {
				arrCopy[ac] = rightCopy[r];
				r++;
			}
			ac++;
		}
		while(l < leftCopy.length){
				arrCopy[ac] = leftCopy[l];
				l++;
				ac++;	
		}
		while(r < rightCopy.length){
				arrCopy[ac] = rightCopy[r];
				r++;
				ac++;	
		}
	}
	static long quickSort(int[] arr){
		int arrCopy[] = new int[arr.length];
		arrCopy = arr.clone();

		long timeMS = System.nanoTime();

		qSort(arrCopy, 0, arrCopy.length - 1);

		return System.nanoTime() - timeMS;
	}
	static void qSort(int[] arrCopy, int first, int last){
		if(first < last){
			int pivotPoint = partition(arrCopy, first, last);
			qSort(arrCopy, first, pivotPoint - 1);
			qSort(arrCopy, pivotPoint + 1, last);
		}
	}
	static int partition(int[] arrCopy, int first, int last){
		int pivot = arrCopy[last];
		int pivotPoint = first;
		for(int i = first; i < last; i++){
			if (arrCopy[i] <= pivot){
				int tmp = arrCopy[i];
				arrCopy[i] = arrCopy[pivotPoint];
				arrCopy[pivotPoint] = tmp;
				pivotPoint++;
			}
		}
		int tmp = arrCopy[last];
		arrCopy[last] = arrCopy[pivotPoint];
		arrCopy[pivotPoint] = tmp;
		return pivotPoint;
	}
}