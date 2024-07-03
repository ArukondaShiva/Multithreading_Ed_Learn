package org.example.mergesortusingmultitreading;

public class MergeSortNaive {

    static void merge(int arr[],int l,int mid,int r){

        int merged[] = new int[r-l+1];

        int ind1 = l;
        int ind2 = mid+1;

        int k = 0;

        while(ind1<=mid && ind2<=r){
            if(arr[ind1]<=arr[ind2]){
                merged[k++] = arr[ind1++];
            }else{
                merged[k++] = arr[ind2++];
            }
        }

        while(ind1<=mid){
            merged[k++] = arr[ind1++];
        }

        while(ind2<=r){
            merged[k++] = arr[ind2++];
        }

        int j = l;

        for(int i=0;i<merged.length;i++){
            arr[j++] = merged[i];
        }

    }


    static void mergeSort(int arr[],int l,int r){

        if(l<r){
            int mid = (l+r)/2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid+1,r);
            merge(arr,l,mid,r);
        }

    }


    public static void runTest(){

        int arr[] = {38, 27, 43, 3, 9, 82, 10};

        mergeSort(arr,0,arr.length-1);

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }

    }



}
