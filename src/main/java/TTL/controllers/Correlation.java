package TTL.controllers;

import TTL.models.Branch;
import TTL.models.Order;

import java.util.ArrayList;
import java.util.List;

public class Correlation {

    private static int[] branchCodeLabelEncoder(List<Order> orders, List<Branch> branches)
    {
        ArrayList<String> branchCodes = Getters.getBranchCodes(branches);
        int ordersSize = orders.size();
        int[] branchCodeLE = new int[ordersSize];
        int i = 0;
        for(Order order : orders)
        {
            branchCodeLE[i] = branchCodes.indexOf(order.getBranchCode().toUpperCase());
            i++;
        }
        System.out.println(ordersSize);
        return branchCodeLE;
    }

    private static int[] orderTypeLabelEncoder(List<Order> orders)
    {
        ArrayList<String> orderTypes = Getters.getOrderTypes(orders);
        int ordersSize = orders.size();
        int[] orderTypeLE = new int[ordersSize];
        int i = 0;
        for(Order order : orders)
        {
            orderTypeLE[i]= orderTypes.indexOf(order.getOrderType());
            i++;
        }
        System.out.println(ordersSize);
        return orderTypeLE;
    }

    private static float correlationCoefficient(int X[], int Y[])
    {
        int sum_X = 0, sum_Y = 0, sum_XY = 0,n = X.length;
        int squareSum_X = 0, squareSum_Y = 0;

        for (int i = 0; i < n; i++)
        {
            // sum of elements of array X.
            sum_X = sum_X + X[i];

            // sum of elements of array Y.
            sum_Y = sum_Y + Y[i];

            // sum of X[i] * Y[i].
            sum_XY = sum_XY + X[i] * Y[i];

            // sum of square of array elements.
            squareSum_X = squareSum_X + X[i] * X[i];
            squareSum_Y = squareSum_Y + Y[i] * Y[i];
        }
        // use formula for calculating correlation
        // coefficient.
        float corr = (float)(n * sum_XY - sum_X * sum_Y)/
                (float)(Math.sqrt((n * squareSum_X -
                        sum_X * sum_X) * (n * squareSum_Y -
                        sum_Y * sum_Y)));
        return corr;
    }

    public static float getCorrelationOfOrderTypesAndBranchCodes(List<Order> orders,List<Branch> branches)
    {
        int[] orderTypeLE = orderTypeLabelEncoder(orders);
        int[] branchCodesLE = branchCodeLabelEncoder(orders,branches);
        return correlationCoefficient(orderTypeLE,branchCodesLE);
    }
}
