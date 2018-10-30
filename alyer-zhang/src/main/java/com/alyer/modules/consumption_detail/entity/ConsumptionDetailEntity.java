package com.alyer.modules.consumption_detail.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 消费明细
 * 
 * @author zhangshengbo
 * @email 669434283@qq.com
 * @date 2018-10-30 15:28:06
 */
@TableName("tb_consumption_detail")
public class ConsumptionDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 消费日期
	 */

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date consumeDate;
	/**
	 * 类别
	 */
	private String type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 单价
	 */
	private BigDecimal amount;
	/**
	 * 数量
	 */
	private Integer number;
	/**
	 * 总计
	 */
	private BigDecimal total;
	/**
	 * 创建者
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改时间
	 */
	private Date updatedDate;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 获取：消费日期
	 */
	public Date getConsumeDate() { return consumeDate; }

	/**
	 * 设置：消费日期
	 */
	public void setConsumeDate(Date consumeDate) { this.consumeDate = consumeDate; }

	/**
	 * 设置：类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：描述
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：单价
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：数量
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：数量
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * 设置：总计
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	/**
	 * 获取：总计
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建者
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
}
