export class Profile {

    constructor(
        private _userName: string,
        private _password: string,
        private _profilePicture: string,
        private _visibility: string,
        private _buddies: number,
        private _requests: number
    ){}

    public get visibility(): string {
        return this._visibility;
    }
    public set visibility(value: string) {
        this._visibility = value;
    }
    public get profilePicture(): string {
        return this._profilePicture;
    }
    public set profilePicture(value: string) {
        this._profilePicture = value;
    }
    public get password(): string {
        return this._password;
    }
    public set password(value: string) {
        this._password = value;
    }
    public get userName(): string {
        return this._userName;
    }
    public set userName(value: string) {
        this._userName = value;
    }

    public get requests(): number {
        return this._requests;
    }
    public set requests(value: number) {
        this._requests = value;
    }
    public get buddies(): number {
        return this._buddies;
    }
    public set buddies(value: number) {
        this._buddies = value;
    }
}
