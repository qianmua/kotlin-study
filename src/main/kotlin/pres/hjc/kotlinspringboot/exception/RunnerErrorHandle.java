package pres.hjc.kotlinspringboot.exception;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/10
 * @time 17:48
 */
public class RunnerErrorHandle extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public RunnerErrorHandle() {
        super();
    }

    public RunnerErrorHandle(ExceptionBase exceptionBase) {
        super(exceptionBase.getErrorCode());
        this.errorCode = exceptionBase.getErrorCode();
        this.errorMsg = exceptionBase.getErrorMsg();
    }

    public RunnerErrorHandle(ExceptionBase exceptionBase, Throwable cause) {
        super(exceptionBase.getErrorCode(), cause);
        this.errorCode = exceptionBase.getErrorCode();
        this.errorMsg = exceptionBase.getErrorMsg();
    }

    public RunnerErrorHandle(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public RunnerErrorHandle(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RunnerErrorHandle(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
