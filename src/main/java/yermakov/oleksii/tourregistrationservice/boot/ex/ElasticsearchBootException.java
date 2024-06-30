package yermakov.oleksii.tourregistrationservice.boot.ex;

public class ElasticsearchBootException extends RuntimeException {
    public ElasticsearchBootException(String message) {
        super(message);
    }
}
