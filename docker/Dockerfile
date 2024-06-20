FROM postgres:16

ENV POSTGRES_USER user
ENV POSTGRES_PASSWORD dbpassword
ENV POSTGRES_DB tour_registration

#docker build -t my-postgres . #
#docker run -d --name postgres-continer -p 5432:5432 my-postgres #
#docker exec -it postgres-container bash #
#psql -U user -d tour_registration #

sudo docker run -d --name elasticsearch --net bridge -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.17.22
sudo docker pull docker.elastic.co/logstash/logstash:7.17.22
docker run -d --name my-logstash \
    -v /path/to/your/logstash.conf:/usr/share/logstash/pipeline/logstash.conf \
    docker.elastic.co/logstash/logstash:<version>
