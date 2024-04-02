FROM rabbitmq:3.12-management
ADD rabbitmq.config /etc/rabbitmq/
ADD definitions.json /etc/rabbitmq/
ENV RABBITMQ_CONFIG_FILE /etc/rabbitmq/rabbitmq
EXPOSE 5672 15672