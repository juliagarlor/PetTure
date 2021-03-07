export class Picture {
    
    constructor(
        private _id: number,
        private _pictureName: string,
        private _userName: string,
        private _licks: number
    ){}

    public get licks(): number {
        return this._licks;
    }
    public set licks(value: number) {
        this._licks = value;
    }
    public get userName(): string {
        return this._userName;
    }
    public set userName(value: string) {
        this._userName = value;
    }
    public get pictureName(): string {
        return this._pictureName;
    }
    public set pictureName(value: string) {
        this._pictureName = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
}
