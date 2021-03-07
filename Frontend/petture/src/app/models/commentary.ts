export class Commentary {

    constructor(
        private _id: number,
        private _userName: string,
        private _body: string
    ){}

    public get body(): string {
        return this._body;
    }
    public set body(value: string) {
        this._body = value;
    }
    public get userName(): string {
        return this._userName;
    }
    public set userName(value: string) {
        this._userName = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
}
