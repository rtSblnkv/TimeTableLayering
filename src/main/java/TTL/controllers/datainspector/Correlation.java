package TTL.controllers.datainspector;

import TTL.controllers.datainspector.encoder.BranchCodeLabelEncoder;
import TTL.controllers.datainspector.encoder.OrderTypeLabelEncoder;
import TTL.models.Order;

import java.util.List;

public class Correlation {

    private static float correlationCoefficient(double x[], double y[])
    {
        double sum_X = 0, sum_Y = 0, sum_XY = 0,n = x.length;
        double squareSum_X = 0, squareSum_Y = 0;

        for (int i = 0; i < n; i++)
        {
            // sum of elements of array x.
            sum_X = sum_X + x[i];

            // sum of elements of array y.
            sum_Y = sum_Y + y[i];

            // sum of x[i] * y[i].
            sum_XY = sum_XY + x[i] * y[i];

            // sum of square of array elements.
            squareSum_X = squareSum_X + x[i] * x[i];
            squareSum_Y = squareSum_Y + y[i] * y[i];
        }
        // use formula for calculating correlation
        // coefficient.
        return  (float)(n * sum_XY - sum_X * sum_Y)/
                (float)(Math.sqrt((n * squareSum_X -
                        sum_X * sum_X) * (n * squareSum_Y -
                        sum_Y * sum_Y)));
    }

    public static float getCorrelationOfOrderTypesAndBranchCodes(List<Order> orders)
    {
        BranchCodeLabelEncoder bcle = new BranchCodeLabelEncoder();
        double[] branchCodesLE = bcle.labelEncode(orders);

        OrderTypeLabelEncoder otle = new OrderTypeLabelEncoder();
        double[] orderTypeLE = otle.labelEncode(orders);
        return correlationCoefficient(branchCodesLE,orderTypeLE);
    }

}
