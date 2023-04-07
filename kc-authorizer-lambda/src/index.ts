import {APIGatewayTokenAuthorizerHandler} from "aws-lambda";
import * as winston from 'winston';
import { KcUtil } from "./utils/KcUtil";
import { PolicyPayload } from "./models/PolicyPayload";

const AUTH_AWS_REGION = process.env.AUTH_AWS_REGION;
const AUTH_AWS_ACCOUNT_ID = process.env.AUTH_AWS_ACCOUNT_ID;

const logger = winston.createLogger({
    level: 'info',
    format: winston.format.json(),
    transports:[
        new winston.transports.Console()
    ]
});

export const handler: APIGatewayTokenAuthorizerHandler = async (event, context) => {
    logger.info('Event: ' + event);
    logger.info('Context: ' + JSON.stringify(context));
    const token = event.authorizationToken;
    const payload: PolicyPayload = {
        principalId: 'user|kcatucuamba',
        resource: event.methodArn,
        effect: 'Allow'
    };
    if(!KcUtil.validateToken(token)) {
        logger.info('Unauthorized');
        payload.effect = 'Deny';
    }
    logger.info('Authorized');
    const policy = await KcUtil.generatePolicy(payload);
    logger.info('Policy: ' +policy);
    return policy;
}