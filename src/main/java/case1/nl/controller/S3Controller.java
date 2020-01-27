package case1.nl.controller;

import case1.nl.util.FMessage;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class S3Controller implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private String s3URI;

    private AWSCredentials credentials;

    AmazonS3 s3client;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    public String getS3URI() {
        return s3URI;
    }

    public void setS3URI(String s3URI) {
        this.s3URI = s3URI;
    }

    //</editor-fold>
    @PostConstruct
    public void init() {

        credentials = new BasicAWSCredentials(
                "AKIAWHSTNMJ6NQZLMHMQ",
                "+tuhq4PG7rYUx2zD4oHbQazdofAm8Q24opkmb4dmv");
    }

    public void generateURL() {        
        
        if (credentials != null) {
            try {
                s3client = AmazonS3ClientBuilder
                        .standard()
                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .withRegion(Regions.EU_CENTRAL_1)
                        .build();

//                List<Bucket> buckets = s3client.listBuckets();
//                buckets.forEach((bucket) -> {
//                    FMessage.info(bucket.getName());
//                });

                s3URI = s3client.getS3AccountOwner().getDisplayName();

            } catch (Exception e) {
                FMessage.error(e.getMessage());
            }

        } else {
            FMessage.warn("No Credentials");
        }
    }

}
