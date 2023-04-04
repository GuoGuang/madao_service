package com.madao.config.chain;

/**
 * 基于责任链模式自由搭配处理器
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-08-20 23:23
 */
public abstract class AbstractCommonHandler<T> {
	protected AbstractCommonHandler<T> next = null;
	public abstract void doHandler(T t) ;
	protected void checkNextNode(T t) {
		if(next!=null){
			next.doHandler(t);
		}
	}
	public void next(AbstractCommonHandler<T> handler) {
		this.next = handler;
	}
	public static class Builder<T> {
		private AbstractCommonHandler<T> head;
		private AbstractCommonHandler<T> tail;

		public Builder<T> addHandler(AbstractCommonHandler<T> handler) {
			if (this.head == null) {
				this.head = handler;
				this.tail = handler;
			} else {
				this.tail.next(handler);
				this.tail = handler;
			}
			return this;
		}

		public AbstractCommonHandler<T> build() {
			return this.head;
		}
	}
}