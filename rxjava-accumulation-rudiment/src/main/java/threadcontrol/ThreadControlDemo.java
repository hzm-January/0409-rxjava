package threadcontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Package: threadcontrol
 * Author: houzm
 * Date: Created in 2018/10/6 1:13
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： 线程控制测试
 */
public class ThreadControlDemo {
    private static Logger logger = LoggerFactory.getLogger(ThreadControlDemo.class);

    public static void main(String[] args) {
//        noControl();
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(11);
                logger.debug(" observable current thread " + Thread.currentThread().getName());
            }
        });
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                logger.debug(" observer onnext ");
                logger.debug(" observer current thread " + Thread.currentThread().getName());
            }
        };
//        observable.subscribe(consumer);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io()).subscribe(consumer);
//        observable.subscribeOn(Schedulers.newThread()).doOnNext(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                logger.debug(" subscribeOn onnext first config ");
//            }
//        }).subscribeOn(Schedulers.io()).doOnNext(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                logger.debug(" subscribeOn onnext second config ");
//            }
//        }).observeOn(Schedulers.newThread()).doOnNext(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                logger.debug(" observeOn onnext first config ");
//            }
//        }).observeOn(Schedulers.computation()).doOnNext(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                logger.debug(" observeOn onnext second config ");
//            }
//        }).observeOn(Schedulers.single()).doOnNext(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                logger.debug(" observeOn onnext third config ");
//            }
//        }).subscribe(consumer);

    }

    private static void noControl() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(12);
                logger.debug(" observable current thread " + Thread.currentThread().getName());
            }
        });
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                logger.debug(" observer onnext ");
                logger.debug(" observer current thread " + Thread.currentThread().getName());
            }
        };
        observable.subscribe(consumer);
    }
}
