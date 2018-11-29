package rudiment.subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Package: rudiment.subscribe
 * Author: houzm
 * Date: Created in 2018/10/5 23:05
 * Copyright: Copyright (c) 2018
 * Version: 0.0.1
 * Modified By:
 * Description： subscribe
 *
 */
public class SubscribeTest {
    private static Logger logger = LoggerFactory.getLogger(SubscribeTest.class);

    public static void main(String[] args) {

        noParams();
        oneParamOfOnNext();
        twoParamsForNextAndError();
        allParams();

    }

    private static void allParams() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
                observableEmitter.onError(new IllegalStateException("observable-->call onError"));
                observableEmitter.onNext(4);
                observableEmitter.onNext(5);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                logger.debug("observer--accpet--for--onnext");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                logger.debug("observer--accpet--for--onerror");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                logger.debug("observer--accpet--for--onComplete");
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                logger.debug("observer--accpet--for--onsubscribe");
            }
        });
    }

    private static void twoParamsForNextAndError() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
                observableEmitter.onError(new IllegalStateException("observable-->call onError"));
                observableEmitter.onNext(4);
                observableEmitter.onNext(5);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                logger.debug("observer--accpet--for--onnext");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                logger.debug("observer--accpet--for--onerror");
            }
        });
    }

    private static void oneParamOfOnNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
                observableEmitter.onNext(4);
                observableEmitter.onNext(5);
            }
        }).subscribe(new Consumer<Integer>() { //只关心onNext
            @Override
            public void accept(Integer integer) throws Exception {
                logger.debug("observer--accept--onnext");
            }
        });
    }

    private static void noParams() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
                observableEmitter.onNext(4);
                observableEmitter.onNext(5);
            }
        }).subscribe();
    }

}
