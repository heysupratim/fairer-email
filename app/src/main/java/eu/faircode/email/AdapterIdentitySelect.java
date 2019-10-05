package eu.faircode.email;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterIdentitySelect extends ArrayAdapter<TupleIdentityEx> {
    private Context context;
    private List<TupleIdentityEx> identities;
    private boolean composeFlag = false;
    private Typeface boldTypeface;

    AdapterIdentitySelect(@NonNull Context context, List<TupleIdentityEx> identities) {
        super(context, 0, identities);
        this.context = context;
        this.identities = identities;
    }

    public void setComposeFlag(boolean composeFlag) {
        this.composeFlag = composeFlag;
        boldTypeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getLayout(position, convertView, parent, R.layout.spinner_item2, false);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getLayout(position, convertView, parent, R.layout.spinner_item2_dropdown, true);
    }

    View getLayout(int position, View convertView, ViewGroup parent, int resid, boolean isDropDown) {
        View view = LayoutInflater.from(context).inflate(resid, parent, false);

        TupleIdentityEx identity = identities.get(position);

        View vwColor = view.findViewById(R.id.vwColor);
        TextView text1 = view.findViewById(android.R.id.text1);
        TextView text2 = view.findViewById(android.R.id.text2);

        vwColor.setBackgroundColor(identity.color == null ? Color.TRANSPARENT : identity.color);
        if(composeFlag){
            vwColor.setVisibility(View.GONE);
            text1.setTextColor(context.getResources().getColor(R.color.black));
            if(isDropDown){
                text1.setTypeface(boldTypeface);
                text1.setText(identity.accountName + "/" + identity.getDisplayName() + (identity.primary ? " ★" : ""));
                text2.setText(identity.email);
                text2.setVisibility(View.VISIBLE);
            }else{
                text1.setText(identity.email);
                text2.setVisibility(View.GONE);
            }
        }else{
            text1.setText(identity.accountName + "/" + identity.getDisplayName() + (identity.primary ? " ★" : ""));
            text2.setText(identity.email);
        }

        return view;
    }
}
