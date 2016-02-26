#import <Cordova/CDV.h>

@interface PayPlugin : CDVPlugin

- (void) createPayment:(CDVInvokedUrlCommand*)command;
@property (nonatomic, strong) NSString* myCallbackId;

@end
