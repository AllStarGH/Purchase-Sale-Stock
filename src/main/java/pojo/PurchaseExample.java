package pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public PurchaseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPurchaseIdIsNull() {
            addCriterion("purchase_id is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdIsNotNull() {
            addCriterion("purchase_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdEqualTo(Integer value) {
            addCriterion("purchase_id =", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotEqualTo(Integer value) {
            addCriterion("purchase_id <>", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdGreaterThan(Integer value) {
            addCriterion("purchase_id >", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_id >=", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdLessThan(Integer value) {
            addCriterion("purchase_id <", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_id <=", value, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdIn(List<Integer> values) {
            addCriterion("purchase_id in", values, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotIn(List<Integer> values) {
            addCriterion("purchase_id not in", values, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdBetween(Integer value1, Integer value2) {
            addCriterion("purchase_id between", value1, value2, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andPurchaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_id not between", value1, value2, "purchaseId");
            return (Criteria) this;
        }

        public Criteria andCommodityIsNull() {
            addCriterion("commodity is null");
            return (Criteria) this;
        }

        public Criteria andCommodityIsNotNull() {
            addCriterion("commodity is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityEqualTo(String value) {
            addCriterion("commodity =", value, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityNotEqualTo(String value) {
            addCriterion("commodity <>", value, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityGreaterThan(String value) {
            addCriterion("commodity >", value, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityGreaterThanOrEqualTo(String value) {
            addCriterion("commodity >=", value, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityLessThan(String value) {
            addCriterion("commodity <", value, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityLessThanOrEqualTo(String value) {
            addCriterion("commodity <=", value, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityLike(String value) {
            addCriterion("commodity like", value, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityNotLike(String value) {
            addCriterion("commodity not like", value, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityIn(List<String> values) {
            addCriterion("commodity in", values, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityNotIn(List<String> values) {
            addCriterion("commodity not in", values, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityBetween(String value1, String value2) {
            addCriterion("commodity between", value1, value2, "commodity");
            return (Criteria) this;
        }

        public Criteria andCommodityNotBetween(String value1, String value2) {
            addCriterion("commodity not between", value1, value2, "commodity");
            return (Criteria) this;
        }

        public Criteria andIsAgreeIsNull() {
            addCriterion("is_agree is null");
            return (Criteria) this;
        }

        public Criteria andIsAgreeIsNotNull() {
            addCriterion("is_agree is not null");
            return (Criteria) this;
        }

        public Criteria andIsAgreeEqualTo(Integer value) {
            addCriterion("is_agree =", value, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeNotEqualTo(Integer value) {
            addCriterion("is_agree <>", value, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeGreaterThan(Integer value) {
            addCriterion("is_agree >", value, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_agree >=", value, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeLessThan(Integer value) {
            addCriterion("is_agree <", value, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeLessThanOrEqualTo(Integer value) {
            addCriterion("is_agree <=", value, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeIn(List<Integer> values) {
            addCriterion("is_agree in", values, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeNotIn(List<Integer> values) {
            addCriterion("is_agree not in", values, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeBetween(Integer value1, Integer value2) {
            addCriterion("is_agree between", value1, value2, "isAgree");
            return (Criteria) this;
        }

        public Criteria andIsAgreeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_agree not between", value1, value2, "isAgree");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(String value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(String value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(String value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(String value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(String value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(String value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLike(String value) {
            addCriterion("supplier like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotLike(String value) {
            addCriterion("supplier not like", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<String> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<String> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(String value1, String value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(String value1, String value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIsNull() {
            addCriterion("amount_money is null");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIsNotNull() {
            addCriterion("amount_money is not null");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyEqualTo(Float value) {
            addCriterion("amount_money =", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotEqualTo(Float value) {
            addCriterion("amount_money <>", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyGreaterThan(Float value) {
            addCriterion("amount_money >", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("amount_money >=", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyLessThan(Float value) {
            addCriterion("amount_money <", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyLessThanOrEqualTo(Float value) {
            addCriterion("amount_money <=", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIn(List<Float> values) {
            addCriterion("amount_money in", values, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotIn(List<Float> values) {
            addCriterion("amount_money not in", values, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyBetween(Float value1, Float value2) {
            addCriterion("amount_money between", value1, value2, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotBetween(Float value1, Float value2) {
            addCriterion("amount_money not between", value1, value2, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNull() {
            addCriterion("payment_method is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNotNull() {
            addCriterion("payment_method is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodEqualTo(Integer value) {
            addCriterion("payment_method =", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotEqualTo(Integer value) {
            addCriterion("payment_method <>", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThan(Integer value) {
            addCriterion("payment_method >", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment_method >=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThan(Integer value) {
            addCriterion("payment_method <", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThanOrEqualTo(Integer value) {
            addCriterion("payment_method <=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIn(List<Integer> values) {
            addCriterion("payment_method in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotIn(List<Integer> values) {
            addCriterion("payment_method not in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodBetween(Integer value1, Integer value2) {
            addCriterion("payment_method between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("payment_method not between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andIsPayIsNull() {
            addCriterion("is_pay is null");
            return (Criteria) this;
        }

        public Criteria andIsPayIsNotNull() {
            addCriterion("is_pay is not null");
            return (Criteria) this;
        }

        public Criteria andIsPayEqualTo(Integer value) {
            addCriterion("is_pay =", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotEqualTo(Integer value) {
            addCriterion("is_pay <>", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayGreaterThan(Integer value) {
            addCriterion("is_pay >", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_pay >=", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayLessThan(Integer value) {
            addCriterion("is_pay <", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayLessThanOrEqualTo(Integer value) {
            addCriterion("is_pay <=", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayIn(List<Integer> values) {
            addCriterion("is_pay in", values, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotIn(List<Integer> values) {
            addCriterion("is_pay not in", values, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayBetween(Integer value1, Integer value2) {
            addCriterion("is_pay between", value1, value2, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotBetween(Integer value1, Integer value2) {
            addCriterion("is_pay not between", value1, value2, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreIsNull() {
            addCriterion("is_enter_store is null");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreIsNotNull() {
            addCriterion("is_enter_store is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreEqualTo(Integer value) {
            addCriterion("is_enter_store =", value, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreNotEqualTo(Integer value) {
            addCriterion("is_enter_store <>", value, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreGreaterThan(Integer value) {
            addCriterion("is_enter_store >", value, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_enter_store >=", value, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreLessThan(Integer value) {
            addCriterion("is_enter_store <", value, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreLessThanOrEqualTo(Integer value) {
            addCriterion("is_enter_store <=", value, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreIn(List<Integer> values) {
            addCriterion("is_enter_store in", values, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreNotIn(List<Integer> values) {
            addCriterion("is_enter_store not in", values, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreBetween(Integer value1, Integer value2) {
            addCriterion("is_enter_store between", value1, value2, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andIsEnterStoreNotBetween(Integer value1, Integer value2) {
            addCriterion("is_enter_store not between", value1, value2, "isEnterStore");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeIsNull() {
            addCriterion("purchase_time is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeIsNotNull() {
            addCriterion("purchase_time is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeEqualTo(Date value) {
            addCriterion("purchase_time =", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeNotEqualTo(Date value) {
            addCriterion("purchase_time <>", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeGreaterThan(Date value) {
            addCriterion("purchase_time >", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("purchase_time >=", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeLessThan(Date value) {
            addCriterion("purchase_time <", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("purchase_time <=", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeIn(List<Date> values) {
            addCriterion("purchase_time in", values, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeNotIn(List<Date> values) {
            addCriterion("purchase_time not in", values, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeBetween(Date value1, Date value2) {
            addCriterion("purchase_time between", value1, value2, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("purchase_time not between", value1, value2, "purchaseTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table purchase
     *
     * @mbggenerated do_not_delete_during_merge Tue Sep 10 10:00:38 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table purchase
     *
     * @mbggenerated Tue Sep 10 10:00:38 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}