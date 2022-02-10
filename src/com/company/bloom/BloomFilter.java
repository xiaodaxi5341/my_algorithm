package com.company.bloom;

/**
 * 布隆过滤器：
 *      1，通过计算对象的hash值，再取模，将对应位数的状态改变；需要准备k种hash算法
 *      并分别计算。
 *
 *      2，经过计算后，bitmap上边的标记就会改变，当再来一个对象的时候，通过上述计算
 *      并比对bitmap的标记，如果一致，则说明该对象已经存在于过滤器中。
 *
 *      3，PS：可能会存在不同的对象，各个hash算法计算的值都一致问题，这就是失误率，
 *      这个失误率一般很小。
 *
 */
public class BloomFilter {



    /**
     * 布隆过滤器实际需要的空间大小
     * @param allSampleSize  样本整体量
     * @param errorRate 预计失误率
     * @return 需要的空间大小
     */
    public double space(double allSampleSize,double errorRate){
        return  Math.ceil(-((allSampleSize*Math.log(errorRate))/(Math.pow(Math.log(2),2))));
    }

    /**
     * 需要准备的hash算法个数
     * @param space 上述方法算出的实际空间大小
     * @param allSampleSize 整体样本量
     * @return hash算法数量
     */
    public double hashFunctionNum(double space,double allSampleSize){
        return Math.ceil(Math.log(2)*(space/allSampleSize));
    }

    /**
     * 真实失误率
     * @param hashFunctionNum hash算法数量
     * @param allSampleSize 整体样本量
     * @param space 所需空间大小
     * @return 实际的失误率
     */
    public double realErrorRate(double hashFunctionNum,double allSampleSize,double space){
        double exponent = -((allSampleSize*hashFunctionNum)/space);
        return Math.pow(1 - Math.log(exponent), hashFunctionNum);
    }

}
