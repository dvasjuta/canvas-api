package edu.ksu.canvas.requestOptions;

public class GetUserCustomDataOptions extends BaseOptions {

    private String scope; //appended to the URL after /custom_data/
    private Integer userId;

    public String getScope() {
	return scope;
    }

    public void setScope(String scope) {
	this.scope = scope;
    }

    public Integer getUserId() {
	return userId;
    }

    public void setUserId(Integer userId) {
	this.userId = userId;
    }


    /**
     * Namespace to fetch the custom data for
     * @param namespace Unique namespace string
     * @return This object to allow adding more options
     */
    public GetUserCustomDataOptions namespace(String namespace) {
        addSingleItem("ns", namespace);
        return this;
    }

}
