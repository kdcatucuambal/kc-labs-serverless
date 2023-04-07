import { APIGatewayAuthorizerResultContext } from "aws-lambda";

export interface Policy {
    principalId: string;
    policyDocument: PolicyDocument;
    context?: Context
}

export interface PolicyDocument {
    Version: string;
    Statement: Statement[];
}

export interface Statement {
    Action: string;
    Effect: string;
    Resource: string[];
}

export interface Context {
    key: string;
    number: number;
    bool: boolean;
}
