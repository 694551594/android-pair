package cn.yhq.pair.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;

/**
 * Created by Yanghuiqiang on 2017/3/14.
 */

public class EditTextPairItem extends PairItem<EditTextPairItem> {
    private String text;
    private String hint;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            text = s.toString();
        }
    };

    public EditTextPairItem(Context context) {
        this(context, null);
    }

    public EditTextPairItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.EdiTextPairItem);

        this.text = a.getString(R.styleable.BaseTextPairItem_text);
        this.hint = a.getString(R.styleable.EdiTextPairItem_hint);

        a.recycle();
    }

    public String getText() {
        return text;
    }

    public String getHint() {
        return hint;
    }

    public EditTextPairItem setHint(String hint) {
        this.hint = hint;
        this.notifyChange();
        return this;
    }

    public EditTextPairItem setText(String text) {
        this.text = text;
        this.notifyChange();
        return this;
    }

    public EditTextPairItem setText(Object text) {
        this.text = text.toString();
        return setText(this.text);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        super.onBindViewHolder(viewHolder);
        String value = this.getText();
        viewHolder.bindResId(R.id.text)
                .setText(TextUtils.isEmpty(value) ? "" : Html.fromHtml(value));
        this.setWidgetView(viewHolder.getView(R.id.text));

        EditText editText = viewHolder.getView(R.id.text);
        editText.setHint(getHint());
        editText.addTextChangedListener(mTextWatcher);
        this.setWidgetView(editText);
    }

    @Override
    public int getWidgetLayoutResource() {
        return R.layout.pair_widget_edittext;
    }

}
